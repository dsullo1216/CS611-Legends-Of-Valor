# CS611-Legends-Of-Valor

## Name
---------------------------------------------------------------------------
David Sullo, Manish Patel

dsullo@bu.edu, mpatel27@bu.edu

## Files
---------------------------------------------------------------------------
<.java file /> - <1 line comment about the file />
<Board.java> Abstract board class for implementing Board-based games. It holds a 2-D array of the Piece class and allows us to manipulate the board and check board status
<Accessible.java> Abstract class to represent a cell where heroes can walk. Will be extended to become Battle Cells and Market Cells 
<Armor.java> Subclass of the Item class that represents armor that can be worn by the hero.
<BattleCell.java> Subclass of AccessibleCell that represents commonspaces where enemies can be encountered
<BattleUI.java> Subclass of UserInterface that represents User Interface where Battle occurs. Implements all necessary methods
<BuffCell.java> Subclass of AccessibleCell that represents a cell where the user will receive a temporary buff if they remain for a turn
<BushCell.java> Subclass of BuffCell that represents a cell where the user will receive a dexterity boost
<CaveCell.java> Subclass of BuffCell that represents a cell where the user will receive an agility boost
<Cell.java> Abstract class to represent the Cells on the map
<Dragon.java> Specialized version of Monster that includes title
<Entity.java> Abstract class to serve as a template for Heroes and Monsters to fight one another
<Exoskeleton.java> Specialized version of the Monster class that includes title
<FireSpell.java> Specialized version of Spell that includes specific debuffs
<Game.java> Abstract class to represent any Game using Scanner
<Hero.java> Subclass of Entity to represent Heroes. Will be extended by special types of Heroes.
<HeroesAndMonstersGame.java> Extension of the RPGGame class that implements the additional methods needed for Heroes and Monster
<HeroesAndMonstersMap.java> // Extension of the map class that includes specific functionality needed for Heroes and Monsters
<HeroSquad.java> Represents party that the user controls. Extends the Squad class
<IceSpell.java> Specialized version of Spell that includes specific debuffs
<InaccessibleCell.java> Subclass of the Cell class that represents cells where the user cannot walk
<Inventory.java> Class that represents either a hero's inventory or the inventory of a shop
<Item.java> Abstract class representing Items for implementing Heroes and Monsters.
<KoulouCell.java> Subclass of BuffCell that represents a cell where the user will receive a strenght boost
<LegendsOfValorGame.java> Extension of the RPGGame class that implements the additional methods needed for Legends of Valor
<LegendsOfValorMap.java> Extension of the map class that includes specific functionality needed for Legends of Valor
<LightningSpell.java> Specialized version of Spell that includes specific debuffs
<Main.java> Main method that simply invokes an instance of the game
<Map.java> Collection class that holds instances of Cells to represent the map. Includes methods to randomize the map.
<MarketCell.java> Subclass of AccessibleCell that represents a cell where the user can go into the market
<MarketUI.java> Subclass of UserInterface that represents the market where player can buy and sell items 
<Monster.java> Subclass of Entity that represents a mob for the hero to fight against. Implements all necessary methods.
<MonsterSquad.java> Represents a hoarde of monsters to be fought during a battle
<NexusCell.java> Subclass of MarketCell that represents a cell where the user can go into the market and teleport back to in LOV
<Paladin.java> Specific version of Hero class that includes specialized version of level up
<PlainCell.java> Subclass of AccessibleCell that represents a cell where the user can stand in LOV
<Potion.java> Subclass of Item to represent Potion items and their attributes. Includes all necessary methods
<ReadFiles.java> Class to handle reading all of the TSV files supplied
<RPGGame.java> Extension of the Game class that implements the methods needed for a Role Playing Game and to be extended by a specific game
<Sorcerer.java> Specialized type of Hero that includes specialized version of level up
<Spell.java> Subclass of Weapon that implements additional features needed for Spell to work.
<Spirit.java> Subclass of Monster that has special stats for the Spirit class
<Squad.java> Abstract class that represents a collection of Heroes. Will be used for player's party as well as hoardes of mobs
<TSVReader.java> Class that simply lets us read a TSV file and export it as a String[]
<UserInterface.java> Abstract class that includes functions/headers for all other UI classes
<UserInventoryUI.java> Subclass of UserInterface class to represent User Interface where Users can manage their inventory. Includes all necessary methods
<Warrior.java> Subclass of Hero to represent Warrior class. Implements specialized version of level_up
<Weapon.java> Subclass of Items that represents Weapons that Heroes can use in battle. Implements necessary methods and additional ones
## Notes
---------------------------------------------------------------------------
## How to run
---------------------------------------------------------------------------
1. Navigate to the main Project Directory after downloading the files
2. Run command line from within the src folder
3. Run the following instructions on command line:
javac *.java
java Main