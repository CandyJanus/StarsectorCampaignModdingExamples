package packageName;

import com.fs.starfarer.api.campaign.InteractionDialogAPI;
import com.fs.starfarer.api.campaign.rules.MemoryAPI;
import com.fs.starfarer.api.impl.campaign.intel.bar.BarEventDialogPlugin;
import com.fs.starfarer.api.impl.campaign.rulecmd.BaseCommandPlugin;
import com.fs.starfarer.api.impl.campaign.rulecmd.missions.BarCMD;
import com.fs.starfarer.api.util.Misc;
import data.campaign.rulecmd.vicTest.called_vic_RBE1;

import java.util.List;
import java.util.Map;

//note: This class will be called as dictated by rules.csv, and in turn call the actual barEvent. This is necessary because if you try to call "BarEvent" directly through rules.csv, it'll CTD and complain that you can't cast to bar event. It's how it's done in vanilla.

//note: To be perfectly honest I don't really understand the plugins here and how they open the dialog options, just that they do.

public class BarEventCaller extends BaseCommandPlugin {
    @Override
    public boolean execute(String ruleId, InteractionDialogAPI dialog, List<Misc.Token> params, Map<String, MemoryAPI> memoryMap) {
        BarEvent event1 = new BarEvent();
        BarCMD cmd = (BarCMD) getEntityMemory(memoryMap).get("$BarCMD");
        BarEventDialogPlugin plugin = new BarEventDialogPlugin(cmd, dialog.getPlugin(), event1, memoryMap);
        dialog.setPlugin(plugin);
        plugin.init(dialog);
        return true;
    }
}
