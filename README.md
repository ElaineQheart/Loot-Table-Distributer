# Loot Table Distributer

This plugin adds 4 commands:

    /loot_distributer reload_file [reloads the tablelist.yml file if there were any changes]
    /loot_distributer range [sets the range in which you can modify chests/barrels]
    /loot_distributer start [toggles the state on]
    /loot_distributer stop [toggles it off]

When loot_distributer is toggled on, the contents of every chest and barrel you look onto are replaced with a random loot table specified in tablelist.yml (there's separate loot table pools for chests and for barrels). Chests and barrels you look onto are cleared from their contents before the loot table is applied. Chests and barrels you look onto are also marked until you run "/loot_distributer stop".
