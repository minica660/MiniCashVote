package MiniCash.miniCashVote;


import MiniCash.miniCashVote.commands.Maincommand;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.*;


public class Publick {


    private static List<String> questions = new ArrayList<>();
    static Map<UUID, Integer> playervote = new HashMap<>();  //プレイヤーが何に投票したかを記録
    private static Map<Integer,Integer> voteresult = new HashMap<>();
    //変数
    public static boolean votecheck = false;

    //投票実行
    public static void vote(String title,int argcount,String[] args){


        Bukkit.broadcastMessage("§k aaaa §r==== 質問" + "§e§l" + title + " §r====§k aaa §r");

        for (int i = 0; i < argcount; i++) {
            questions.add(args[i + 2]);

            Bukkit.broadcastMessage("§2§l" + (i + 1) + " §6" + questions.get(i) );
        }

        Bukkit.broadcastMessage("  ========================");

        votecheck = true; //投票中かどうかの判定をtrueに変更
    }


    //投票中かのチェック
    public static boolean votingcheck(Player player) {
        if (votecheck) {
            player.sendMessage("§c現在進行中の投票があります");
            player.sendMessage("§c現在進行中の投票を停止させてからもう一度実行させてください");

            return votecheck;

        }

        return votecheck;
    }

    //投票中かのチェック（投票中じゃない場合のみ）
    public static boolean votingcheckv2(Player player){
        if (!votecheck){
            player.sendMessage("§c現在進行中の投票がありません");
            player.sendMessage("§c投票が始まるまでお待ちください");
            return votecheck;
        }
        return votecheck;
    }

    public static void votev(Player player,Integer number){
        UUID id = player.getUniqueId();

        int numberResult = number - 1;
        String question = questions.get(numberResult);
        //すでに投票しているかのチェック
        if (playervote.containsKey(id)){
            player.sendMessage("§cエラー：§lすでに投票しています");
            return;
        }

        //投票番号をMapに入れる
        playervote.put(id,number);

        //投票確認メッセージを送信
        player.sendMessage("§e§l" + number + "：§r§e" + question + "§r§lに投票しました！");

    }

    //何人のプレイヤーが投票しているのか
    public static void playercheck(Player player){

        int playercheckv = playervote.size();
        int onlineplayercount = Bukkit.getOnlinePlayers().size();
        player.sendMessage("§6投票数：§r" + playercheckv + "人");
        player.sendMessage("§6全体人数：§r " + onlineplayercount + "人");
        player.sendMessage("§a投票数 §7" + playercheckv + "/" + onlineplayercount + "人");

    }

    //投票結果
    public static void voteresult(Player player){


        //プレイヤーごとに投票番号を取り出す
        for (UUID id : playervote.keySet()) {
            int votenumber = playervote.get(id);

            //投票番号の投票結果保存用Map内にあるデータを取り出す
            int vplayerv = voteresult.getOrDefault(votenumber,0);

            vplayerv++;
            voteresult.put(votenumber,vplayerv); //投票番号のkeyのところに数を＋１
        }

        //投票数をMapから検知
        //int voteresultsize = voteresult.size();
        int votecount = Maincommand.argscount.getFirst(); //質問数をもとに

        int i = 0;

        //結果全体通知
        player.sendMessage("§k00000 §r§d§l投票結果 §r§k00000");

        while (i < votecount){

            int vresult = voteresult.getOrDefault(i + 1,0);
            Bukkit.broadcastMessage( (i + 1 ) + questions.get(i) +"§7投票数：§b" + vresult + "§r票");

            i++;
        }

        //投票完全リセットメソッド呼び出し
        votereset(player);

    }

    //投票停止＆リセット
    public static void votereset(Player player){

        player.sendMessage("§e§l投票がリセットされました！！");

        //MapやListのデータ初期化
        playervote.clear();
        voteresult.clear();
        questions.clear();

        //投票中をfalseに
        votecheck = false;


    }

    public static void help(Player player){
        player.sendMessage("§2投票作成：§l/vote create <質問タイトル> <質問1> <質問2>　・・・");
        player.sendMessage("§2投票：§l/vote vote <投票番号>");
        player.sendMessage("§2投票チェック：§l/vote vcheck");
        player.sendMessage("§2投票終了：§l/vote vstop");
        player.sendMessage("§2投票完全リセット：§l/vote stop");
    }

}

