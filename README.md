# Starsector Mod Examples

The master branch of the Git repo is blank, but each branch will have examples of starsector campaign features to copy. I'd have preferred that this be made by an experienced modder, instead of someone fumbling around trying to figure things out as they do it. It is what it is. For the love of the moon, if you see a problem, and there will be problems I'm not aware of, please leave a comment remarking upon it.

# Bar Event Example Breakdown

This template is a simple implementation mostly pulled from Wisp's tutorial at https://starsector.fandom.com/wiki/Modding_Quests and from Histidine's assistance. It is a single event, not a quest chain, and is relatively bare-bones.

It has the following files:

mod_info.json - All mods must have this.

rules.csv - Defines the triggers of a quest. You can use code instead of rules.csv, might do that in another example template.

BarEventCaller - In this template, triggered by rules.csv. Can be called via code.

BarEvent - Called by BarEventCaller. There are good reasons why BarEventCaller and BarEvent are separate classes, detailed in the notes within BarEventCaller.

# Extra notes

You can probably make this template compile faster by having it *not* require every dang mod, but this template expansion is designed for "barely familiar with coding at all" levels of experience. 





