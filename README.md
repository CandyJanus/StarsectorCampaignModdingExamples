# Starsector Mod Examples

The master branch of the Git repo is blank, but each branch will have examples of starsector campaign features to copy. I'd have preferred that this be made by an experienced modder, instead of someone fumbling around trying to figure things out as they do it. It is what it is. For the love of the moon, if you see a problem, and there will be problems I'm not aware of, please leave a comment remarking upon it.

# Bar Event Example Breakdown

This template is a simple implementation mostly pulled from Wisp's tutorial at https://starsector.fandom.com/wiki/Modding_Quests and from Histidine's assistance.

It has the following files:

mod_info.json - All mods must have this.
ModPluginName.java - mod_info.json labels this as a file that must be called. ModPluginName.java itself, here, is used to initialize all the other scripts that need to run for this bar quest. 
rules.csv - Defines the triggers of a quest. You can use code instead of rules.csv, might do that in another example template.


# Extra notes

You can probably make this template compile faster by having it *not* require every dang mod, but this template expansion is designed for "barely familiar with coding at all" levels of experience. 





