package com.example;

import com.fs.starfarer.api.EveryFrameScript;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.*;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.campaign.rules.MemoryAPI;
import com.fs.starfarer.api.characters.PersonAPI;
import com.fs.starfarer.api.impl.campaign.ids.Ranks;
import exerelin.campaign.MarketDescChanger;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.BaseCampaignEventListener;
import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.CommDirectoryAPI;
import com.fs.starfarer.api.campaign.CommDirectoryEntryAPI;
import com.fs.starfarer.api.campaign.CommDirectoryEntryAPI.EntryType;
import
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.campaign.rules.MemoryAPI;
import com.fs.starfarer.api.characters.PersonAPI;
import com.fs.starfarer.api.impl.campaign.ids.Ranks;
import java.util.Iterator;
import org.apache.log4j.Logger;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;


import java.util.*;

public class ShipDescChanger extends BaseCampaignEventListener implements EveryFrameScript{

    public static final List<DescUpdateEntry> BASE_DESCRIPTIONS = new ArrayList<>();
    public static final Map<String, List<DescUpdateEntry>> DESCRIPTIONS_BY_ENTITY_ID = new HashMap<>();
    public static final String CSV_PATH = "data/config/list_of_changed_descriptions.csv";

    public static Logger log = Global.getLogger(ShipDescChanger.class);
    public static boolean loadedDefs = false;
    Map<String, Object> data = Global.getSector().getPersistentData();

    public ShipDescChanger(boolean permaRegister) {
        super(permaRegister);
    }

//    public ShipDescChanger(int num) {
//        super();
//        loadDefs(num);
//    }
//
//    public static void loadDefs(int num) {
//        if (loadedDefs) return;
//        loadedDefs = true;
//        try {
//            JSONArray csv = Global.getSettings().getMergedSpreadsheetDataForMod("id", CSV_PATH, "desc_changer_wm_thing");
//
//            for (int i = 0; i < csv.length(); i++) {
//                JSONObject row = csv.getJSONObject(i);
//                String id = row.getString("id");
//                if (id == null || id.isEmpty()) continue;
//                log.info("Modified ship desc " + id);
//
//                String entityId = row.getString("entity");
//                String factionId = row.getString("faction");
//                String descId = row.getString("desc id");
//                if (descId.isEmpty()) descId = null;
//                boolean isDefault = row.optBoolean("default", false);
//
//                DescUpdateEntry desc = new DescUpdateEntry(entityId, descId, isDefault);
//                BASE_DESCRIPTIONS.add(desc);
//                if (!DESCRIPTIONS_BY_ENTITY_ID.containsKey(entityId)) {
//                    DESCRIPTIONS_BY_ENTITY_ID.put(entityId, new ArrayList<DescUpdateEntry>());
//                }
//                DESCRIPTIONS_BY_ENTITY_ID.get(entityId).add(desc);
//            }
//        } catch (IOException | JSONException ex) {
//            throw new RuntimeException("Failed to load base ship descs", ex);
//        }
//    }



//    public static void setShipDesc()
//    {
//        String entityId = entity.getId();
//        if (DESCRIPTIONS_BY_ENTITY_ID.containsKey(entityId))
//        {
//            List<MarketDescChanger.DescUpdateEntry> descs = DESCRIPTIONS_BY_ENTITY_ID.get(entityId);
//            for (MarketDescChanger.DescUpdateEntry desc : descs) {
//                if (!desc.factionId.equals(factionId)) continue;
//                entity.setCustomDescriptionId(desc.descId);
//                log.info("Updating entity " + entityId + " description to " + desc.descId);
//                return;
//            }
//
//            for (MarketDescChanger.DescUpdateEntry desc : descs) {
//                if (desc.isDefault) {
//                    entity.setCustomDescriptionId(desc.descId);
//                    return;
//                }
//            }
//        }
//    }

    public static class DescUpdateEntry {
        public final String entityId;
        public final String descId;
        public final boolean isDefault;

        public DescUpdateEntry(String entityId, String descId, boolean isDefault)
        {
            this.entityId=entityId;
            this.descId = descId;
            this.isDefault = isDefault;
        }
    }

    @Override
    public boolean isDone() {
        return false;
    }

    @Override
    public boolean runWhilePaused() {
        return false;
    }

    @Override
    public void advance(float amount) {

    }

//    public void reportPlayerOpenedMarket(MarketAPI market) {
//        if(market.getFactionID()=="sindria"){
//
//
//            //something something add "This ship class has been blessed by a visit to the Supreme Executor's homeworld?"
//        }
//    }
}

