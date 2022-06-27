package com.example;

import com.fs.starfarer.api.EveryFrameScript;
import com.fs.starfarer.api.Global;
import exerelin.ExerelinConstants;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShepherdDescChanger implements EveryFrameScript {

    public static final List<DescUpdateEntry> DESCRIPTIONS = new ArrayList<>();
    public static final Map<String, List<DescUpdateEntry>> DESCRIPTIONS_BY_ENTITY_ID = new HashMap<>();
    public static final String CSV_PATH = "data/config/list_of_changed_descriptions.csv";
    public static boolean loadedDefs = false;

    public static void loadDefs() {
        if (loadedDefs) return;
        loadedDefs = true;
        try {
            JSONArray csv = Global.getSettings().getMergedSpreadsheetDataForMod("id", CSV_PATH, ExerelinConstants.MOD_ID);
            for (int i = 0; i < csv.length(); i++) {
                JSONObject row = csv.getJSONObject(i);
                String id = row.getString("id");
                if (id == null || id.isEmpty()) continue;
                log.info("Adding captured planet desc row " + id);

                String entityId = row.getString("entity");
                String factionId = row.getString("faction");
                String descId = row.getString("desc id");
                if (descId.isEmpty()) descId = null;
                boolean isDefault = row.optBoolean("default", false);

                DescUpdateEntry desc = new DescUpdateEntry(entityId, factionId, descId, isDefault);
                DESCRIPTIONS.add(desc);
                if (!DESCRIPTIONS_BY_ENTITY_ID.containsKey(entityId)) {
                    DESCRIPTIONS_BY_ENTITY_ID.put(entityId, new ArrayList<DescUpdateEntry>());
                }
                DESCRIPTIONS_BY_ENTITY_ID.get(entityId).add(desc);
            }
        } catch (IOException | JSONException ex) {
            throw new RuntimeException("Failed to load captured planet descs", ex);
        }
    }

    public MarketDescChanger() {
        loadDefs();
    }

    public static class DescUpdateEntry {
        public final String entityId;
        public final String factionId;
        public final String descId;
        public final boolean isDefault;

        public DescUpdateEntry(String entityId, String factionId, String descId, boolean isDefault)
        {
            this.entityId = entityId;
            this.factionId = factionId;
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

