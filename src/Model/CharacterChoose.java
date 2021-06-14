package Model;

import java.io.Serializable;

public class CharacterChoose implements Serializable {
    private static CharacterChoose characterChoose;
    private String characterName;
    //private String characterDirection;
    private String url;

    public CharacterChoose() //constructor
    {
        characterName = "SpongeBob";
        url = "/pictures/player.png";
    }

    //singleton
    public static CharacterChoose getInstance()
    {
        if (characterChoose == null) {
            characterChoose = new CharacterChoose();
        }
        return characterChoose;
    }

    public String getCharacterName() {
        return characterName;
    }

    public String getUrl() { return url; }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setCharacter(String characterName, String url) {
        this.characterName = characterName;
        this.url = url;
    }



}
