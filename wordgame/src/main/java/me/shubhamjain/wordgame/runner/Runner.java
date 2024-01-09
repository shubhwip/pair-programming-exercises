package me.shubhamjain.wordgame.runner;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;

@Slf4j
public class Runner {

    public static void main(String[] args) {
        if (args == null || args.length == 0) {
            log.error("Please provide starting string");
            throw new IllegalArgumentException("Please provide correct arguments");
        }
        String startingString = args[0];
        IGameRunner gameRunner = new DefaultIGameRunner(startingString);
        gameRunner.run();
        Random r = new Random();
        String s ="zithernzithernszitherszitizitiszitszizitzizithzizzlezizzledzizzleszizzlingzlotezlotieszlotyzlotychzlotyszoazoantharianzoantharianszoariazoarialzoariumzodiaczodiacalzodiacszoeazoeaezoealzoeaszoeciazoeciumzoftigzoiczoisitezoisiteszombizombiezombielikezombieszombificationzombificationszombifiedzombifieszombifyzombifyingzombiismzombiismszombiszonalzonallyzonaryzonatezonatedzonationzonationszonezonedzonelesszonerzonerszoneszonetimezonetimeszoningzonkzonkedzonkingzonkszonulazonulaezonularzonulaszonulezonuleszoozoochorezoochoreszooeciazooeciumzoogeniczoogeographerzoogeographerszoogeographiczoogeographicalzoogeographicallyzoogeographieszoogeographyzoogleazoogleaezooglealzoogleaszoogloeazoogloeaezoogloeaszooidzooidalzooidszookeeperzookeeperszookszoolaterzoolaters";
        for(int i=0;i<s.length()-2;i+=2) {
            int n = r.nextInt(6);
            System.out.print("\"" + s.substring(i, n+i) + "\"" + ",");
        }
    }
}
