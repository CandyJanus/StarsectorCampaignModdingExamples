package com.example;

import com.fs.starfarer.api.BaseModPlugin;
import com.fs.starfarer.api.Global;
import com.thoughtworks.xstream.XStream;
import data.scripts.sevencorp_ModPlugin;
import sun.rmi.runtime.Log;

import java.util.Map;

public class ModPluginName extends BaseModPlugin {

    private static org.apache.log4j.Logger log = Global.getLogger(ModPluginName.class);

    public void onNewGame(boolean newGame) {
//        Global.getSector().addScript(new ShipDescChanger(true));
    }

    @Override
    public void onGameLoad(boolean newGame) {
        super.onGameLoad(newGame);

//        Global.getSector().addTransientScript(saver);
//        Global.getSector().addTransientListener(saver);
//        Log.debug("Added autosaver to sector");
//
//        int num;
//        Map<String, Object> data = Global.getSector().getPersistentData();
//        if(data!=null)
//        {
//            if(!data.containsKey("number_of_times_game_loaded"))
//            {
//                data.put("number_of_times_game_loaded",0);
//            }
//            else{
//                num=(int)data.get("number_of_times_game_loaded");
//                num++;
//                data.put("number_of_times_game_loaded",num);
//            }
//            num=(int)data.get("number_of_times_game_loaded");
//            log.info("This save has been loaded " + num + " times.");
        }
    }

    @Override
    public void afterGameSave()
    {
//        saver.resetTimeSinceLastSave();
    }

    /**
     * Tell the XML serializer to use custom naming, so that moving or renaming classes doesn't break saves.
     */
    @Override
    public void configureXStream(XStream x) {
        super.configureXStream(x);
        // This will make it so that whenever "ExampleEveryFrameScript" is put into the save game xml file,
        // it will have an xml node called "ExampleEveryFrameScript" (even if you rename the class!).
        // This is a way to prevent refactoring from breaking saves, but is not required to do.

        // x.alias("ExampleEveryFrameScript", ExampleEveryFrameScript.class);
    }
}