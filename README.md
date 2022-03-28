# DO NOT USE THIS BRANCH, IT DOES NOT WORK

I had an issue where it spawned the bar event exactly once, and after it was dismissed, never again. I then deleted something that seemed apparently redundant without checking what it was, and then permanently broke it. Don't do that. 

# Starsector Mod Examples

The master branch of the Git repo is blank, but each branch will have examples of starsector campaign features to copy. I'd have preferred that this be made by an experienced modder, instead of someone fumbling around trying to figure things out as they do it. It is what it is. For the love of the moon, if you see a problem, and there will be problems I'm not aware of, please leave a comment remarking upon it.

# Bar Event Example Breakdown

This template is a simple implementation mostly pulled from Wisp's tutorial at https://starsector.fandom.com/wiki/Modding_Quests and from Histidine's assistance. It is a single bar event, not a quest chain, and is relatively bare-bones.

It has the following files:

mod_info.json - All mods must have this.

BarEvent - This specific bar event spawns a random NPC, changes its gender, and allows for it to be made into a trading contact. 



# Extra notes

You can probably make this template compile faster by having it *not* require every dang mod, but this template expansion is designed for "barely familiar with coding at all" levels of experience. 


# Problems

Currently fighting an issue where the event runs once acceptably, but then on being loaded again, fails to offer any option and only displays the addPara text in INIT of barevent.java. Console gives me:
____

139529 [Thread-3] INFO  com.fs.starfarer.loading.scripts.B  - Loading class: packageName.BarEvent$OptionId
139529 [Thread-3] INFO  com.fs.starfarer.loading.scripts.B  - Loading class: packageName.BarEvent$1
139529 [Thread-3] INFO  com.fs.starfarer.loading.scripts.B  - Loading class: java.lang.NoSuchFieldError
____

which ain't nice.


