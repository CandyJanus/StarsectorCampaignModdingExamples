package packageName;

import com.fs.starfarer.api.campaign.InteractionDialogAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.campaign.rules.MemoryAPI;
import com.fs.starfarer.api.impl.campaign.intel.bar.events.BaseBarEventWithPerson;

import java.util.Map;

//note: PortsideBarEvent is the most basic event class, and it has a number of implementations, such as BaseBarEventWithPerson and HubMissionWithBarEvent. These can be extended instead of BaseBarEvent. I choose to extend BaseBarEventWithPerson here as it's the most generally useful. Unsurprisingly, it has prebuilt methods for generating characters.

public class BarEvent extends BaseBarEventWithPerson {

    //note: This is the list of dialogue options that will be called. They can be arbitrarily named anything at all.
    public static enum OptionId {
        INIT,
        FUCK_OFF_1,
        APOLOGIZE_1,
        FUCK_OFF_2,
        EXIT,
    }

    //note: planetaryShieldBarEvent declares the repeating superloop. I dunno if one has to, but I do it.
    public BarEvent() {
        super();
    }

    public boolean shouldShowAtMarket(MarketAPI market) {
        //note: Add whatever conditions you want here. Or you could put your conditions in rules.csv instead if you like.
        return super.shouldShowAtMarket(market) && market.getFactionId().equals("hegemony");
    }

    @Override
    public void addPromptAndOption(InteractionDialogAPI dialog, Map<String, MemoryAPI> memoryMap) {
        // Calling super does nothing in this case, but is good practice because a subclass should
        // implement all functionality of the superclass (and usually more)
        super.addPromptAndOption(dialog, memoryMap);
        // Sets field variables and creates a random person
        //note: regen is a method from BaseBarEventWithPerson, not the basic BaseBarEvent.
        regen(dialog.getInteractionTarget().getMarket());

        // Display the text that will appear when the player first enters the bar and looks around
        //note: the grammar methods are found in PersonAPI.java
        dialog.getTextPanel().addPara("An smooth-skinned seal in Hegemony uniform stares at you from across the bar. "+ "What's" + getHeOrShe() + " doing? Is " + getHisOrHer() + " hat all askew? What a strange " + getManOrWoman()+ " .;);

        // Display the option that lets the player choose to investigate our bar event
        dialog.getOptionPanel().addOption("Talk to the smooth-skinned seal.", this);
    }

    @Override
    public void init(InteractionDialogAPI dialog, Map<String, MemoryAPI> memoryMap) {
        super.init(dialog, memoryMap);

        // The boolean is for whether to show only minimal person information. True == minimal
        dialog.getVisualPanel().showPersonInfo(person, true);
        // Launch into our event by triggering the "INIT" option, which will call `optionSelected()`
        optionSelected(null, BarEvent.OptionId.INIT);
    }

    @Override
    public void optionSelected(String optionText, Object optionData) {
        if (optionData instanceof OptionId) {
            // Clear shown options before we show new ones
            dialog.getOptionPanel().clearOptions();

            // Handle all possible options the player can choose
            //note: It's not strictly necessary to use a switch function. It's just usually easier.
            switch ((OptionId) optionData) {
                case INIT:
}
