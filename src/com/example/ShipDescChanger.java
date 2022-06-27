package com.example;

import com.fs.starfarer.api.EveryFrameScript;
import com.fs.starfarer.api.Global;
import exerelin.ExerelinConstants;
import exerelin.campaign.MarketDescChanger;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShipDescChanger implements EveryFrameScript {

    public static final List<DescUpdateEntry> BASE_DESCRIPTIONS = new ArrayList<>();
    public static final Map<String, List<DescUpdateEntry>> DESCRIPTIONS_BY_ENTITY_ID = new HashMap<>();
    public static final String CSV_PATH = "data/config/list_of_changed_descriptions.csv";

    public static Logger log = Global.getLogger(ShipDescChanger.class);
    public static ShipDescChanger currInstance;
    public static boolean loadedDefs = false;

    public static void loadDefs() {
        if (loadedDefs) return;
        loadedDefs = true;
        try {
            JSONArray csv = Global.getSettings().getMergedSpreadsheetDataForMod("id", CSV_PATH, "desc_changer_wm_thing");
            for (int i = 0; i < csv.length(); i++) {
                JSONObject row = csv.getJSONObject(i);
                String id = row.getString("id");
                if (id == null || id.isEmpty()) continue;
                log.info("Modified ship desc " + id);

                String entityId = row.getString("entity");
                String factionId = row.getString("faction");
                String descId = row.getString("desc id");
                if (descId.isEmpty()) descId = null;
                boolean isDefault = row.optBoolean("default", false);

                DescUpdateEntry desc = new DescUpdateEntry(entityId, descId, isDefault);
                BASE_DESCRIPTIONS.add(desc);
                if (!DESCRIPTIONS_BY_ENTITY_ID.containsKey(entityId)) {
                    DESCRIPTIONS_BY_ENTITY_ID.put(entityId, new ArrayList<DescUpdateEntry>());
                }
                DESCRIPTIONS_BY_ENTITY_ID.get(entityId).add(desc);
            }
        } catch (IOException | JSONException ex) {
            throw new RuntimeException("Failed to load base ship descs", ex);
        }
    }

    public ShipDescChanger() {
        loadDefs();
    }

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
        return true;
    }

    @Override
    public boolean runWhilePaused() {
        return false;
    }

    @Override
    public void advance(float v) {

    }
}

