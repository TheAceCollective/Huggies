package theacecollective.huggies.commandexecutors;

import theacecollective.huggies.Huggies;
import theacecollective.huggies.Utils;

public class HugCommandExecutor extends AbstractCommandExecutor {

    public HugCommandExecutor(Huggies huggiesPlugin) {
        super(
                huggiesPlugin,
                "eastereggs_hug",
                "The person you want to hug is currently offline.",
                "Only players can send hugs.",
                Utils.FormatMessage("You gave %receiver% a big hug. -{^_^}-", true),
                Utils.FormatMessage("You received a big hug from %sender%. -{^_^}-", true)
        );
    }

}
