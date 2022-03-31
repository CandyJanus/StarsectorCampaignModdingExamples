package packageName.campaign;

import com.fs.starfarer.api.campaign.InteractionDialogAPI;
import com.fs.starfarer.api.campaign.OptionPanelAPI;
import com.fs.starfarer.api.campaign.PersonImportance;
import com.fs.starfarer.api.campaign.TextPanelAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.campaign.rules.MemoryAPI;
import com.fs.starfarer.api.characters.FullName;
import com.fs.starfarer.api.impl.campaign.ids.Ranks;
import com.fs.starfarer.api.impl.campaign.ids.Tags;
import com.fs.starfarer.api.impl.campaign.intel.bar.events.BaseBarEventWithPerson;
import com.fs.starfarer.api.impl.campaign.rulecmd.FireAll;


import java.awt.*;
import java.util.Map;

//note: PortsideBarEvent is the most basic event class, and it has a number of implementations, such as BaseBarEventWithPerson and HubMissionWithBarEvent. These can be extended instead of BaseBarEvent. I choose to extend BaseBarEventWithPerson here as it's the most generally useful. Unsurprisingly, it has prebuilt methods for generating characters.

public class BarEventName extends BaseBarEventWithPerson {

    //note: This is the list of dialogue options that will be called. They can be arbitrarily named anything at all.
    public static enum OptionId {
        INIT,
        APOLOGIZE_1,
        APOLOGIZE_2,
        DOUBLE_DOWN_1,
        EXIT,
    }

    //note: planetaryShieldBarEvent declares the repeating superloop. I dunno if one has to, but I do it.
    public BarEventName() {
        super();
    }

    public boolean shouldShowAtMarket(MarketAPI market) {
        //note: Add whatever conditions you want here. Or you could put your conditions in rules.csv instead if you like.
        return super.shouldShowAtMarket(market) && market.getFactionId().equals("independent");
    }

    @Override
    public void addPromptAndOption(InteractionDialogAPI dialog, Map<String, MemoryAPI> memoryMap) {
        // Calling super does nothing in this case, but is good practice because a subclass should
        // implement all functionality of the superclass (and usually more)
        super.addPromptAndOption(dialog, memoryMap);
        // Sets field variables and creates a random person with variable name of "person."
        //note: regen is a method from BaseBarEventWithPerson, not the basic BaseBarEvent. You don't have to use this - I'll make another tutorial on detailed character creation.
        regen(dialog.getInteractionTarget().getMarket());
        //note: make sure your portrait is 128 x 128 and a .png. I believe UAF does black magic to have larger portraits, but outside of black magic, the SS is unhappy at deviation.
        person.setPortraitSprite("graphics/portraits/seal_portrait.png");

        // Display the text that will appear when the player first enters the bar and looks around
        //note: the grammar methods are found in PersonAPI.java
        dialog.getTextPanel().addPara("An smooth-skinned seal dressed like a filthy neutral stares at you from across the bar. "+ "What's " + getHeOrShe() + " doing? Is " + getHisOrHer() + " hat all askew? What a strange " + getManOrWoman()+ ".");

        // Display the option that lets the player choose to investigate our bar event
        dialog.getOptionPanel().addOption("Talk to the smooth-skinned seal.", this);
    }

    @Override
    public void init(InteractionDialogAPI dialog, Map<String, MemoryAPI> memoryMap) {
        super.init(dialog, memoryMap);

        // The boolean is for whether to show only minimal person information. True == minimal
        dialog.getVisualPanel().showPersonInfo(person, false);


            //note: Had an extremely nasty bug where the event would work once correctly, but then on a second try, would fail to function correctly. Went through various iterations like creating more functioning event but leaving behind defective copies, or having IndexOutOfBoundsError 2 of 2 because it didn't clear the previous event or something.
            //note: The following lines in this init function are from Histidine, to bugfix the issue.

        // Launch into our event by triggering the "INIT" option, which will call `optionSelected()`
        super.init(dialog, memoryMap);
        done = false; // this is the important part, it resets the bar event state so it doesn't end immediately after
    }

    @Override
    public void optionSelected(String optionText, Object optionData) {
        if (optionData instanceof OptionId) {
            // Clear shown options before we show new ones
            dialog.getOptionPanel().clearOptions();

            BarEventName.OptionId option = (BarEventName.OptionId) optionData;

            OptionPanelAPI options = dialog.getOptionPanel();
            TextPanelAPI text = dialog.getTextPanel();
            options.clearOptions();

            // Handle all possible options the player can choose
            //note: It's not strictly necessary to use a switch function. It's just usually easier.
            switch(option){
                case INIT:
                    text.addPara("The seal unleashes an unholy wail.");
                    options.addOption("\"You alright?\"", OptionId.APOLOGIZE_1);
                    options.addOption("Punch the defective marine mammal in its silly little nose.", OptionId.DOUBLE_DOWN_1);
                    break;
                case DOUBLE_DOWN_1:
                    if (person.getGender()==FullName.Gender.FEMALE){
                        person.setGender(FullName.Gender.MALE);
                        text.addPara("You've changed this seal from female to male!", Color.red);
                        text.addPara("You punch the weak little creature in the face and turn the flabby thing into a real man.");
                    }
                    else{
                        person.setGender(FullName.Gender.FEMALE);
                        //note: this doesn't check for the Gender.Any case, I couldn't be bothered
                        text.addPara("You've changed this seal from male to female!", Color.red);
                        text.addPara("You punch the weak little creature in the face and turn the flabby thing into a real woman.");
                    }
                    text.addPara("The seal wails again, disoriented by your gender-distorting fist-donation.");
                    options.addOption("Do it again.", BarEventName.OptionId.DOUBLE_DOWN_1);
                    options.addOption("Solid hit. There's nothing more to be done here.", BarEventName.OptionId.EXIT);
                    break;
                case APOLOGIZE_1:
                    text.addPara("The seal sniffs.");
                    text.addPara("\"Yeah, I'm alright. Sorry about the bother.\" "+ getHeOrShe() +" says. \"Life's just been beating me down recently. Don't trouble yourself about it.\"" );
                    options.addOption("Life does not forgive weakness. Punch the seal in the face.", OptionId.DOUBLE_DOWN_1);
                    options.addOption("\"I understand. Life's been hard this cycle.\"", OptionId.APOLOGIZE_2);
                    break;
                case APOLOGIZE_2:
                    text.addPara("The seal smiles weakly. \"You're a good one. Bright stars and hard burns to ye.\"");
                    person.setRankId(Ranks.POST_TRADER);
                    person.setImportance(PersonImportance.MEDIUM);
                    person.addTag(Tags.CONTACT_TRADE);
                    options.addOption("\"You too, friend.\"", BarEventName.OptionId.EXIT);
                case EXIT:
                    noContinue=true;
                    done=true;

                    //note: I think this is necessary to clear indices or something.  I have no idea exactly what it does.
                    FireAll.fire(null, dialog, memoryMap, "PopulateOptions");
                    break;
            }
        }
    }
}
