# Starsector Mod Examples

The master branch of the Git repo is blank, but each branch will have examples of starsector campaign features to copy. I'd have preferred that this be made by an experienced modder, instead of someone fumbling around trying to figure things out as they do it. It is what it is. For the love of the moon, if you see a problem, and there will be problems I'm not aware of, please leave a comment remarking upon it.

# Bar Event Example Breakdown

This template is a simple implementation mostly pulled from Wisp's tutorial at https://starsector.fandom.com/wiki/Modding_Quests and from Histidine's assistance.

It has the following files:

mod_info.json - All mods must have this.
ModPluginName.java - mod_info.json labels this as a file that must be called. ModPluginName.java itself, here, is used to initialize all the other scripts that need to run for this bar quest. 
rules.csv - Defines the triggers of a quest. You can use code instead of rules.csv, might do that in another example template.



# Extra notes

You can use bar_events.csv instead of a BarEventCreator file. That's simpler, but supports less possible behavior. I personally prefer to use code instead of .csvs where possible, because starsector seems to always be reliable at hotswapping code files, but is finicky in ways I don't comprehend when reloading .csvs in dev mode. My one exception is using rules.csv, which I find more readable to coworkers than raw code. At least, I find it easier to read rules.csv than something more technically sophisticated like Wisp's Persean Chronicles. Possibly this is due to weak mental on my part.

You can probably make this template compile faster by having it *not* require every dang mod, but this template expansion is designed for "barely familiar with coding at all" levels of experience. 





