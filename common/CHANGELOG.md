# CHANGELOG
Many of you may know that Naturalist has a much larger, in-depth add-on on Bedrock that is paid. A lot of people have had grievances with this, so we wanted to take the opportunity to add this to the changelog -- it being paid on Bedrock does not mean we are abandoning the Java version, and the income from it allows us to keep creating Java mods for free while paying our team hourly wages.

Thank you all for your patience.

- ### Added SmartBodyHelpers and better pathfinding, courtesy of Bob Mowzie. Mobs now smoothly turn and should not spin in circles.
- ### Mobs should no longer slide / be animation-less.
- ### Added an actual, in-depth config!
   - "Mob Removal" config to disable individual mobs without the need for external mods.
   - Spawn rate configs should now work properly - previously if set to 0 they would not actually disable mobs from spawning.
   - Mob removal will remove mobs similar to monsters being removed in Peaceful mode - they will not spawn and any previously-existing mobs will be removed from your world.
   - Alongside spawn rate configs, we have added spawn group modifiers to better control spawn numbers.
- ### Added more data pack customization!
   - We have added some compatibility with other mods like Atmospheric _(courtesy of Crabbarition)_ and welcome people to make Pull Requests to our GitHub repository to contribute more.
   - Mobs can now be blacklisted from biomes - most mobs, like Alligators and Dragonflies, should no longer spawn in cold swamp biomes added by other mods.
- ### Merged translations from GitHub
   - Naturalist is now available in the following languages: 
      - Ukrainian
      - Taiwanese Mandarin
      - Russian
      - Japanese
- ### Fixed Glow Goop duplication
- ### Dragonflies now spawn as "Ambient" instead of "Creature"
- ### Vultures now drop held items when killed
- ### Boars now spawn properly
- ### Added compatibility/integration for the "Diet" mod
