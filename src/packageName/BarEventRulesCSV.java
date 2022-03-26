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

public class BarEventRulesCSV extends BaseCommandPlugin {
    @Override
    public boolean execute(String ruleId, InteractionDialogAPI dialog, List<Misc.Token> params, Map<String, MemoryAPI> memoryMap) {
        called_vic_RBE1 rbe1 = new called_vic_RBE1();
        //note: This is pretty weird but it's how it's done in vanilla (BarCMD.java:105)
        //note: you need to do it that way because if you try to call "called_vic_RBE1" directly through rules.csv, it'll CTD and complain that you can't cast to bar event or something.
        BarCMD cmd = (BarCMD) getEntityMemory(memoryMap).get("$BarCMD");
        BarEventDialogPlugin plugin = new BarEventDialogPlugin(cmd, dialog.getPlugin(), rbe1, memoryMap);
        dialog.setPlugin(plugin);
        plugin.init(dialog);
        return true;
    }
}
