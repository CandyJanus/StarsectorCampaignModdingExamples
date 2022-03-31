
# Starsector Mod Examples

The master branch of the Git repo is blank, but each branch will have examples of starsector campaign features to copy. I'd have preferred that this be made by an experienced modder, instead of someone fumbling around trying to figure things out as they do it. It is what it is. For the love of the moon, if you see a problem, and there will be problems I'm not aware of, please leave a comment remarking upon it.

# Bar Event Example Breakdown

This template is a simple implementation partially pulled from Wisp's tutorial at https://starsector.fandom.com/wiki/Modding_Quests, from Histidine's assistance, and from vanilla code. It is a single bar event, not a quest chain, and is relatively bare-bones.

It has the following files:

mod_info.json - All mods must have this.

settings.json - Specify where your scripts for rules.csv are stored. I have wasted embarrassing amounts of time because I've forgotten you need to do this.

BarEvent - This specific bar event spawns a random NPC, changes its gender, and allows for it to be made into a trading contact. 

BarEventAdderRuleCMD - Calls BarEvent.

rules.csv - Calls BarEventAdderRuleCMD.

bar_events.csv - Regulates how frequently rules.csv calls the listed event. Optional file. 

___________
In order of what activates what:

 rules.csv->BarEventAdderRuleCMD->BarEvent


___________


# Extra notes

You can probably make this template compile faster by having it *not* require every dang mod, but this template expansion is designed for "barely familiar with coding at all" levels of experience. 





