import java.io.IOException;

// Class to handle reading all of the TSV files supplied
public class ReadFiles {
 
    public static Hero[] ListOfHeroes() throws IOException {
        String[] WarriorArr = TSVReader.readTSVFile("./src/data/Warriors.tsv");
        String[] SorcererArr = TSVReader.readTSVFile("./src/data/Sorcerers.tsv");
        String[] PaladinArr = TSVReader.readTSVFile("./src/data/Paladins.tsv");
        Hero[] heroArr = new Hero[WarriorArr.length + SorcererArr.length + PaladinArr.length - 3];
        String[] splitRow;
        int resultIndex = 0;
        for (int i = 1; i < WarriorArr.length; i++) {
            splitRow = WarriorArr[i].split("\t");
            heroArr[resultIndex] = new Warrior(splitRow[0], Integer.valueOf(splitRow[1]), Integer.valueOf(splitRow[2]), Integer.valueOf(splitRow[3]), Integer.valueOf(splitRow[4]), Integer.valueOf(splitRow[5]), Integer.valueOf(splitRow[6]));
            resultIndex++;
        }
        for (int i = 1; i < SorcererArr.length; i++) {
            splitRow = SorcererArr[i].split("\t");
            heroArr[resultIndex] = new Sorcerer(splitRow[0], Integer.valueOf(splitRow[1]), Integer.valueOf(splitRow[2]), Integer.valueOf(splitRow[3]), Integer.valueOf(splitRow[4]), Integer.valueOf(splitRow[5]), Integer.valueOf(splitRow[6]));
            resultIndex++;
        }
        for (int i = 1; i < PaladinArr.length; i++) {
            splitRow = PaladinArr[i].split("\t");
            heroArr[resultIndex] = new Paladin(splitRow[0], Integer.valueOf(splitRow[1]), Integer.valueOf(splitRow[2]), Integer.valueOf(splitRow[3]), Integer.valueOf(splitRow[4]), Integer.valueOf(splitRow[5]), Integer.valueOf(splitRow[6]));
            resultIndex++;
        }
        return heroArr;
    }    

    public static Monster[] ListOfMonsters() throws IOException {
        String[] DragonArr = TSVReader.readTSVFile("./src/data/Dragons.tsv");
        String[] ExoskeletonArr = TSVReader.readTSVFile("./src/data/Exoskeletons.tsv");
        String[] SpiritArr = TSVReader.readTSVFile("./src/data/Spirits.tsv");
        Monster[] monsterArr = new Monster[DragonArr.length + ExoskeletonArr.length + SpiritArr.length - 3];
        String[] splitRow;
        int resultIndex = 0;
        for (int i = 1; i < DragonArr.length; i++) {
            splitRow = DragonArr[i].split("\t");
            monsterArr[resultIndex] = new Dragon(splitRow[0], Integer.valueOf(splitRow[1]), Integer.valueOf(splitRow[2]), Integer.valueOf(splitRow[3]), Integer.valueOf(splitRow[4]));
            resultIndex++;
        }
        for (int i = 1; i < ExoskeletonArr.length; i++) {
            splitRow = ExoskeletonArr[i].split("\t");
            monsterArr[resultIndex] = new Exoskeleton(splitRow[0], Integer.valueOf(splitRow[1]), Integer.valueOf(splitRow[2]), Integer.valueOf(splitRow[3]), Integer.valueOf(splitRow[4]));
            resultIndex++;
        }
        for (int i = 1; i < SpiritArr.length; i++) {
            splitRow = SpiritArr[i].split("\t");
            monsterArr[resultIndex] = new Spirit(splitRow[0], Integer.valueOf(splitRow[1]), Integer.valueOf(splitRow[2]), Integer.valueOf(splitRow[3]), Integer.valueOf(splitRow[4]));
            resultIndex++;
        }
        return monsterArr;
    }

    public static Item[] ListOfItems() throws IOException {
        String[] ArmorArr = TSVReader.readTSVFile("./src/data/Armory.tsv");
        String[] WeaponArr = TSVReader.readTSVFile("./src/data/Weaponry.tsv");
        String[] PotionArr = TSVReader.readTSVFile("./src/data/Potions.tsv");
        String[] IceSpellArr = TSVReader.readTSVFile("./src/data/IceSpells.tsv");
        String[] FireSpellArr = TSVReader.readTSVFile("./src/data/FireSpells.tsv");
        String[] LightningSpellArr = TSVReader.readTSVFile("./src/data/LightningSpells.tsv");
        Item[] itemArr = new Item[ArmorArr.length + WeaponArr.length + PotionArr.length + IceSpellArr.length + FireSpellArr.length + LightningSpellArr.length - 6];
        String[] splitRow;
        int resultIndex = 0;
        for (int i = 1; i < ArmorArr.length; i++) {
            splitRow = ArmorArr[i].split("\t");
            itemArr[resultIndex] = new Armor(splitRow[0], Integer.valueOf(splitRow[1]), Integer.valueOf(splitRow[2]), Integer.valueOf(splitRow[3]));
            resultIndex++;
        }
        for (int i = 1; i < WeaponArr.length; i++) {
            splitRow = WeaponArr[i].split("\t");
            itemArr[resultIndex] = new Weapon(splitRow[0], Integer.valueOf(splitRow[1]), Integer.valueOf(splitRow[2]), Integer.valueOf(splitRow[3]));
            resultIndex++;
        }
        for (int i = 1; i < PotionArr.length; i++) {
            splitRow = PotionArr[i].split("\t");
            itemArr[resultIndex] = new Potion(splitRow[0], Integer.valueOf(splitRow[1]), Integer.valueOf(splitRow[2]), Integer.valueOf(splitRow[3]), splitRow[4]);
            resultIndex++;
        }
        for (int i = 1; i < IceSpellArr.length; i++) {
            splitRow = IceSpellArr[i].split("\t");
            itemArr[resultIndex] = new IceSpell(splitRow[0], Integer.valueOf(splitRow[1]), Integer.valueOf(splitRow[2]), Integer.valueOf(splitRow[3]), Integer.valueOf(splitRow[4]));
            resultIndex++;
        }
        for (int i = 1; i < FireSpellArr.length; i++) {
            splitRow = FireSpellArr[i].split("\t");
            itemArr[resultIndex] = new FireSpell(splitRow[0], Integer.valueOf(splitRow[1]), Integer.valueOf(splitRow[2]), Integer.valueOf(splitRow[3]), Integer.valueOf(splitRow[4]));
            resultIndex++;
        }
        for (int i = 1; i < LightningSpellArr.length; i++) {
            splitRow = LightningSpellArr[i].split("\t");
            itemArr[resultIndex] = new LightningSpell(splitRow[0], Integer.valueOf(splitRow[1]), Integer.valueOf(splitRow[2]), Integer.valueOf(splitRow[3]), Integer.valueOf(splitRow[4]));
            resultIndex++;
        }
        return itemArr;
    }
}