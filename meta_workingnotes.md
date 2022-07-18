#project cancelled

starsector doesn't allow file access by modders using outside libraries







# Overall Objective

Be able to dynamically modify ship descriptions in an in-progress game, not just from statically loaded preset descriptions, but according to events that occur in game. 



# Working Notes

Ships do *not* have a setCustomDescriptionID method. I think we can work around that by modifying the csv directly and trying hacky shit to directly refresh the CSV use. 

__

First, let's try direct modification of the csv. For the sake of safety we will make a backup csv of the descriptions file we modify before we modify it. Later, we may work on more features like global cross-save memory storage to see which modified csv is the correct one. 

We will be using opencsv as the easiest library to use. 







__

Options for proceeding, ranked in terms of preferability:

1. running loadcsv on descriptions just refreshes it
2. I somehow force the game through other means to refresh csv
3. force save and load the game, which would probably work?

Let's bugtest the final solution first. Following the example of Autosave, we will simply use robot.keypress to manually override SS and force it to save and then reload. 


__

Plans for imitating Nex interfacing with all mods seem questionable. With ships, one would need to invasively change descriptions belonging to vanilla and other mods, which is simply a bad idea waiting for trouble. 

However, I DO know that repeated csvs are ignored. If I load this mod first by manipulating the modid, it's possible that I can override the descriptions of vanilla and other mods without touching their files at all. I'm a little bit leery of trying this method because it could be repurposed to fuck with people, but such fuckery would be readily detectable, which balances things out.


