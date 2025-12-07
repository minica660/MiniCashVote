package MiniCash.miniCashVote.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static MiniCash.miniCashVote.Publick.*;



    public class Maincommand implements CommandExecutor {

    public static List<Integer> argscount = new ArrayList<>();



    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        //変数定義1
        Player player = (Player) sender;

        if (args.length == 0){
            help(player);
            return true;
        }

        //変数定義2
        String arg0 = args[0];



        if (arg0.equals("create")) {


            if (args.length < 3) {
                player.sendMessage("§c引数が足りません");
                return true;
            }

            //投票中かどうかをチェックするメソッド呼び出し
            if (votingcheck(player)) {
                //現在進行中の投票を停止させてからもう一度実行させてください
                return true;
            }


            String title = args[1];
            int argcount = args.length - 2;
            //投票作成＆通知メソッド呼び出し
            vote(title, argcount, args);

            //投票数保存
            argscount.add(argcount);


        }else if (arg0.equals("vote")) {
            //引数数が正しいかの検証
            if (args.length < 2) {
                player.sendMessage("§c投票時の引数が足りません");
                return true;
            }

            String argnumber = args[1];
            int number;
            try {
                number = Integer.parseInt(argnumber);

            } catch (NumberFormatException e) {
                // 変換に失敗（数字以外の文字が入力された）した場合の処理

                // ユーザーにエラーメッセージを送信
                sender.sendMessage("§c§l" + argnumber + "§r§cは有効な数字ではありません");

                // コマンド処理を中断し、終了する
                return true;
            }


            if (!votingcheckv2(player)) {
                //現在進行中の投票がありません
                return true;
            }
            int listargscount = argscount.getFirst();

            if (number < 1 || number > listargscount) {
                player.sendMessage("§c§l投票番号が有効ではありません");
                return true;
            }

            votev(player, number);


            return true;
        }else if (arg0.equals("vcheck")) {
            if (!votingcheckv2(player)) {
                //現在進行中の投票がありません
                return true;
            }
            //何人のプレイヤーが投票しているのかチェックコマンド
            playercheck(player); //メソッド呼び出し
            return true;
        }else if (arg0.equals("vstop")) { //投票停止

            //投票がされているかのチェック
            if (!votingcheckv2(player)) {
                //現在進行中の投票がありません
                return true;
            }

            //投票結果表示メソッド呼び出し
            voteresult(player);

            return true;


        }else if (arg0.equals("stop")){

            //投票完全リセットメソッド呼び出し
            votereset(player);

    }else {
        help(player); //helpメソッド呼び代
        return true;
    }
        return true;
    }
}
