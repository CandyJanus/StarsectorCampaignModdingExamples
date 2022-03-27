# Starsector Mod Examples

The master branch of the Git repo is blank, but each branch will have examples of starsector campaign features to copy. I'd have preferred that this be made by an experienced modder, instead of someone fumbling around trying to figure things out as they do it. It is what it is. For the love of the moon, if you see a problem, and there will be problems I'm not aware of, please leave a comment remarking upon it.

# Bar Event Example Breakdown

This template is a simple implementation mostly pulled from Wisp's tutorial at https://starsector.fandom.com/wiki/Modding_Quests and from Histidine's assistance. It is a single bar event, not a quest chain, and is relatively bare-bones.

It has the following files:

mod_info.json - All mods must have this.

BarEvent - This specific bar event spawns a random NPC, changes its gender, and allows for it to be made into a trading contact. 

bar_events.csv - Defines the triggers of a bar event. Techpriest has documentation of it here: https://fractalsoftworks.com/forum/index.php?topic=23001.0

# Extra notes

You can probably make this template compile faster by having it *not* require every dang mod, but this template expansion is designed for "barely familiar with coding at all" levels of experience. 





