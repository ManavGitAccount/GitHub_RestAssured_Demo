package resources;

import pojo.AddName;

public class TestDataBuilder extends Utility {

    public AddName addNamePayLoad(String name){
        AddName addName = new AddName();
        addName.setName(name);

        return addName;
    }

    public AddName addPatchPayLoad(String name){
        AddName addName = new AddName();
        addName.setPathName(name);

        return addName;
    }

    public String deletePlacePayload(){
        return "https://api.github.com/repos/" + getJsonPath(responseObj, "full_name");
    }
}
