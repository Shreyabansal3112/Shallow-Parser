package parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.lang.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class parser {

    public static final String CYAN_BACKGROUND = "\033[46m";   // CYAN
    public static final String WHITE_BACKGROUND = "\033[47m";  // WHITE
    public static final String GREEN_BACKGROUND = "\033[42m";  // GREEN
    public static final String YELLOW_BACKGROUND = "\033[43m"; // YELLOW
    public static final String PURPLE_BACKGROUND = "\033[45m"; // PURPLE
    public static final String RED_BACKGROUND = "\033[46m";   // CYAN
    public static final String BLACK_UNDERLINED = "\033[4;30m";  // BLACK
    public static final String RED_UNDERLINED = "\033[4;31m";    // RED
    public static final String GREEN_UNDERLINED = "\033[4;32m";  // GREEN
    public static final String YELLOW_UNDERLINED = "\033[4;33m"; // YELLOW
    public static final String BLUE_UNDERLINED = "\033[4;34m";   // BLUE
    public static final String PURPLE_UNDERLINED = "\033[4;35m"; // PURPLE
    public static final String CYAN_UNDERLINED = "\033[4;36m";   // CYAN
    public static final String WHITE_UNDERLINED = "\033[4;37m";  // WHITE
    public static int size = 0;
    public static int sen_cnt = 0;
    public static int cnt = 0;

    public static char n = 'n';
    public static char vb = 'v';
    public static char ad = 'j';
    public static char adve = 'a';
    public static char con = 'c';
    public static char sy = 's';
    public static char pp = 'p';
    public static char part = 'r';
    public static char punt = 'u';
    public static char qnt = 'q';
    public static char ne = 'g';

    public static List<Integer> positions = new ArrayList();
     public static List<Integer> posi = new ArrayList();
    public static List<String> noun = new ArrayList();
    public static List<String> verb = new ArrayList();
    public static List<String> adjective = new ArrayList();
    public static List<String> adverb = new ArrayList();
    public static List<String> conjunctions = new ArrayList();
    public static List<String> quantifier = new ArrayList();
    public static List<String> postposition = new ArrayList();
    public static List<String> punctuation = new ArrayList();
    public static List<String> symbol = new ArrayList();
    public static List<String> particle = new ArrayList();
    public static List<String> negation = new ArrayList();
    public static List<String> fulist = new ArrayList();
    public static List<String> tags = new ArrayList();

    public static String postag = "";
    public static String st = "";
    public static String stcpy = "";
    public static String sttcpy = "";
    public static void main(String[] args) throws NullPointerException, IOException {

        String stt = "";

        File fileDir = new File("C:\\Users\\admin\\Downloads\\input output file_noun\\noun group\\hh.txt");

        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(fileDir), "UTF8"));

        String line = null;

        while ((st = in.readLine()) != null) {
            stcpy=st;
            sttcpy=st;
            noun.clear();
            verb.clear();
            adjective.clear();
            adverb.clear();
            conjunctions.clear();
            postposition.clear();
            negation.clear();
            punctuation.clear();
            quantifier.clear();
            symbol.clear();
            particle.clear();
            posi.clear();
            fulist.clear();
            tags.clear();
            sen_cnt = sen_cnt + 1;
            char str[] = st.toCharArray();
            str[str.length - 1] = ' ';
            st = "";
            for (int g = 0; g < str.length; g++) {
                //System.out.println(str[g]);
                st += str[g];
            }
            st = st + "END/END";
            System.out.println("\n\n sentence:" + sen_cnt + " = " + st + "\033[0m" + "\n");
            st = verb(st);
            // System.out.println(CYAN_BACKGROUND + "\n\n sentence:" + sen_cnt + " = " + st + "\033[0m"+"\n");
            st = noun(st);
            //System.out.println(CYAN_BACKGROUND + "\n\n sentence:" + sen_cnt + " = " + st + "\033[0m"+"\n");
            st = adjective(st);
            st = adverb(st);
            st = conjunction(st);
            st = quantifier(st);
            st = negation(st);
            st = particle(st);
            st = symbol(st);
            st = postposition(st);
            st = punctuation(st);
            int z;
            int numb;
            
            if (!noun.isEmpty())
            fulist.addAll(noun);
            numb=noun.size();
            for (z=0;z<numb;z++)
                tags.add("noun");
            
            if (!verb.isEmpty())
            fulist.addAll(verb);
            numb=verb.size();
            for (z=0;z<numb;z++)
                tags.add("verb");
            
            if (!adjective.isEmpty())
            fulist.addAll(adjective);
            numb=adjective.size();
            for (z=0;z<numb;z++)
                tags.add("adjective");
            
            if (!adverb.isEmpty())
            fulist.addAll(adverb);
            numb=adverb.size();
            for (z=0;z<numb;z++)
                tags.add("adverb");
            
            if (!conjunctions.isEmpty())
            fulist.addAll(conjunctions);
            numb=conjunctions.size();
            for (z=0;z<numb;z++)
                tags.add("conjunctions");
            
            if (!postposition.isEmpty())
            fulist.addAll(postposition);
            numb=postposition.size();
            for (z=0;z<numb;z++)
                tags.add("postposition");
            
            if (!symbol.isEmpty())
            fulist.addAll(symbol);
            numb=symbol.size();
            for (z=0;z<numb;z++)
                tags.add("symbol");
            
            if (!quantifier.isEmpty())
            fulist.addAll(quantifier);
            numb=quantifier.size();
            for (z=0;z<numb;z++)
                tags.add("quantifier");
            
            if (!particle.isEmpty())
            fulist.addAll(particle);
            numb=particle.size();
            for (z=0;z<numb;z++)
                tags.add("particle");
            
            if (!negation.isEmpty())
            fulist.addAll(negation);
            numb=negation.size();
            for (z=0;z<numb;z++)
                tags.add("negation");
            
            if (!punctuation.isEmpty())
            fulist.addAll(punctuation);
            numb=punctuation.size();
            for (z=0;z<numb;z++)
                tags.add("punctuation");
            
            
            
            
            st = st.replace("*/ZERO ", "");
            /*if (st.equals("END/END ")) {
                System.out.println(WHITE_BACKGROUND + "no remaining string" + "\n" + "\033[0m");
            } else {
                System.out.println(PURPLE_BACKGROUND + "\nremaining string:" + st + "\n" + "\033[0m");
            }*/
            //String st = stcpy;  //string
            for(String newst:fulist)
            {   
               
              
                posi.add(stcpy.indexOf(newst));
                
                char test[]=stcpy.toCharArray();
               
                test[stcpy.indexOf(newst)+1]=' ';
                  stcpy="";
                 stcpy = String.valueOf(test);
                
                
            }
           fulist=insertion_sort(posi,fulist,tags);
          /* System.out.println(noun);
            System.out.println(verb);
            System.out.println(adjective);
            System.out.println(adverb);
            System.out.println(quantifier);
            System.out.println(conjunctions);
            System.out.println(postposition);
            System.out.println(punctuation);
            System.out.println(particle);
            System.out.println(symbol);
            System.out.println(negation);
           
             
              System.out.println(posi);*/
           for(z=0;z<fulist.size();z++)
             System.out.println(fulist.get(z)+"\n");
           

        }//outer for loop
    }//main

    private static String noun(String st) throws NullPointerException, IOException {

        String splitst[] = st.split(" ");
        String[] split = splitst.clone();
        //System.out.println(stcpy);
        // System.out.println(Arrays.toString(splitst));
        System.out.println();
        //st.concat(" $");
        int l = splitst.length;

        for (int i = 0; i < (l); i++) {
            // System.out.println(splitst[i]);

        }

        //System.out.println("\n\n");
        //System.out.println("\n\nSplitted string in words and tags is:");
        for (int i = 0; i < (l); i++) {
            String[] splitsubst = splitst[i].split("/");
            // System.out.println(splitsubst[0]);
            // System.out.println(splitsubst[1]);
            if (splitsubst.length > 2) {
                splitsubst[0] = "/";
                splitsubst[1] = splitsubst[2];
            }
            splitst[i] = splitsubst[1];
            if ((splitsubst[0].equals("-"))) {
                splitsubst[1] = "PUN";
                splitst[i] = "PUN";
            }
            if ((splitsubst[0].equals("बाद")) || (splitsubst[0].equals("बीच")) || (splitsubst[0].equals("ऊपर")) || (splitsubst[0].equals("मध्य")) || (splitsubst[0].equals("नीचे")) || (splitsubst[0].equals("पीछे")) || (splitsubst[0].equals("पास"))) {
                splitsubst[1] = "LOC";
                splitst[i] = "LOC";
            }
            if ((splitsubst[0].equals(","))) {
                splitsubst[1] = "COMMA";
                splitst[i] = "COMMA";
            }
            if (splitsubst[0].equals(".")) {
                splitsubst[1] = "DOT";
                splitst[i] = "DOT";
            }
            if ((splitsubst[0].equals("'")) || (splitsubst[0].equals("\"")) || (splitsubst[0].equals("`"))) {
                splitsubst[1] = "BRAC";
                splitst[i] = "BRAC";
            }
            if ((splitsubst[0].equals("(")) || (splitsubst[0].equals("{")) || (splitsubst[0].equals("["))) {
                splitsubst[1] = "OPEN";
                splitst[i] = "OPEN";
            }
            if ((splitsubst[0].equals(")")) || (splitsubst[0].equals("}")) || (splitsubst[0].equals("]"))) {
                splitsubst[1] = "CLOSE";
                splitst[i] = "CLOSE";
            }
            if ((splitsubst[0].equals("से"))) {
                splitsubst[1] = "SE";
                splitst[i] = "SE";
            }
            if (splitst[i].equals("P")) {
                splitst[i] = "PR";
            }
            postag += splitst[i] + " ";
            // System.out.println(postag);
        }

        //System.out.println("\n\n");
        for (int i = 0; i < l; i++) {
            //System.out.println(splitst[i]);
        }
        //System.out.println("postag=" + postag);

        String a = "(NST (PUN |DOT )NST |NST )*((DMD QTC DMD )|DMD |DMR |DMQ |DMI )+(PSP |SE |PUN )*(RPD )*";
        String b = "(NST (PUN |DOT )NST |NST )*(PRP |PRF |PRL |PRQ |PRC |PRI |PR )+(PUN )*(PRP |PRF |PRL |PRQ |PRC |PRI |PR )*";
        String c = "((NEG )*(((NN |NNP )(PUN |DOT )QTC )|((QTC |QTF |QTO )+(SYM )*(NEG )*(SE |PUN |CCD |DOT )*(NEG )*(QTC |QTF |QTO )+))(SYM )*(RPD )*(NEG )*(PUN )*)+";
        String d = "(NEG )*(INTF |QTO |QTC |QTF )+(SYM )*(RPD )*(NEG )*";
        String e = "(JJ (COMMMA )*)+(PSP |SE |PUN |SE )*";
        String e1 = "(((NN |NNP )(PUN |DOT )JJ )|JJ (PUNC |PUN |CCD |DOT |COMMA )(ECH )*(JJ |RPD )+(COMMA )*(JJ )*(COMMA )*)+";

        String f = "(NEG )*(((NN |NNP |PRP |PRF |PRL |PRQ |PRC |PRI |PR |NST |LOC )+(PUN |DOT |BRAC )*(((QTC |QTF |QTO )+(SYM )*(NEG )*(SE |PUN |CCD |DOT )*(NEG )*(QTC |QTF |QTO )+)|QTC |QTF |QTO |NST )+(SYM )*(PUN |DOT |BRAC )*(NN |NNP |PRP |PRF |PRL |PRQ |PRC |PRI |PR |NST |LOC )*(PUN |DOT |BRAC )*(NN |NNP |PRP |PRF |PRL |PRQ |PRC |PRI |PR |NST |LOC )*(PUN |DOT |BRAC )*(PSP |SE )*)|((NN |NNP |PRP |PRF |PRL |PRQ |PRC |PRI |PR |NST |LOC )+(PUN |DOT |BRAC )*(QTC |QTF |QTO )+(PUN )*(NN |NNP |PRP |PRF |PRL |PRQ |PRC |PRI |PR |NST |LOC )+(PSP |SE )*)|((NN |NNP |PRP |PRF |PRL |PRQ |PRC |PRI |PR |NST |LOC )+(PUN |DOT |BRAC )+(NN |NNP |PRP |PRF |PRL |PRQ |PRC |PRI |PR |NST |LOC )+(PUN |DOT |BRAC )+(NN |NNP |PRP |PRF |PRL |PRQ |PRC |PRI |PR |NST |LOC )+(PUN |DOT |BRAC )*(NN |NNP |PRP |PRF |PRL |PRQ |PRC |PRI |PR |NST |LOC )*(PUN |DOT |BRAC )*(PSP |SE )*)|((NN |NNP |PRP |PRF |PRL |PRQ |PRC |PRI |PR |NST |LOC )+(PUN |DOT |BRAC )+(NN |NNP |PRP |PRF |PRL |PRQ |PRC |PRI |PR |NST |LOC )+(PUN |DOT |BRAC )*(NN |NNP |PRP |PRF |PRL |PRQ |PRC |PRI |PR |NST |LOC )*(PUN |DOT |BRAC )*(PSP |SE )*)|(((NN |NNP |PRP |PRF |PRL |PRQ |PRC |PRI |PR |RDF )+(PSP |SE )*(NST |LOC )+(PUN )*(NST |LOC )+(PSP |SE )*))|((NST )+(PSP )*((NN |NNP |PRP |PRF |PRL |PRQ |PRC |PRI |PR |RDF )+(PUN |DOT |BRAC )*(PSP |SE )*))|((BRAC )+(NN |NNP |PRP |PRF |PRL |PRQ |PRC |PRI |PR |RDF )+(BRAC )+(PSP |SE )*)|((NN |NNP |PRP |PRF |PRL |PRQ |PRC |PRI |PR |RDF )+(DOT )(PSP |SE )*)|((NN |NNP |PRP |PRF |PRL |PRQ |PRC |PRI |PR |RDF )+(PSP |SE )*)|((NST |LOC )(PSP |SE )*))";

        String g = "(NEG )*(((JJ |QTC |QTF |QTO )+(PUNC |PUN |CCD |DOT |COMMA )(ECH )*(JJ |QTC |QTF |QTO )+(COMMA |PUN )*(JJ |QTC |QTF |QTO )*(COMMA |CCD )*)|(JJ (COMMA )*)|QTF |QTC |QTO |JJ )+(SYM )*(NEG )*(((NST |LOC )PUN (NST |LOC ))|(NST PUN NN )|INTF |RPD |INJ |NST |JJ |LOC )*(PSP |SE )*(?!NN )(INTF |RPD |INJ )*";
        String h = "(NEG )*((NST |LOC )PUN (NST |LOC )|NST PUN NN |INTF |(PUN )*RPD |INJ |NST |LOC )*(PSP |SE )*(NEG )*(?!NN )(PSP |SE )*(RPD )*";
        String p1 = a + b + c + e1 + f + h + g;
        String p2 = a + b + c + e1 + f + h;

        String p3 = b + a + c + e1 + f + h + g;
        String p4 = b + a + c + e1 + f + h;

        String p5 = a + b + d + e1 + f + h + g;
        String p6 = a + b + d + e1 + f + h;

        String p7 = b + a + d + e1 + f + h + g;
        String p8 = b + a + d + e1 + f + h;

        String p9 = a + b + c + e + f + h + g;
        String p10 = a + b + c + e + f + h;

        String p11 = b + a + c + e + f + h + g;
        String p12 = b + a + c + e + f + h;

        String p13 = b + a + d + e + f + h + g;
        String p14 = b + a + d + e + f + h;

        String p15 = a + b + d + e + f + h + g;
        String p16 = a + b + d + e + f + h;

        String p17 = a + b + e1 + f + h + g;
        String p18 = a + b + e1 + f + h;

        String p19 = b + a + e1 + f + h + g;
        String p20 = b + a + e1 + f;

        String p21 = a + b + c + f + h + g;
        String p22 = a + b + c + f + h;

        String p23 = b + a + c + f + h + g;
        String p24 = b + a + c + f + h;

        String p25 = a + b + e + f + h + g;
        String p26 = a + b + e + f + h;

        String p27 = b + a + e + f + h + g;
        String p28 = b + a + e + f + h;

        String p29 = a + b + d + f + h + g;
        String p30 = a + b + d + f + h;

        String p31 = b + a + d + f + h + g;
        String p32 = b + a + d + f + h;

        String p33 = a + c + e1 + f + h + g;
        String p34 = a + c + e1 + f + h;

        String p35 = b + c + e1 + f + h + g;
        String p36 = b + c + e1 + f + h;

        String p37 = a + d + e1 + f + h + g;
        String p38 = a + d + e1 + f + h;

        String p39 = b + d + e1 + f + h + g;
        String p40 = b + d + e1 + f + h;

        String p41 = a + c + e + f + h + g;
        String p42 = a + c + e + f + h;

        String p43 = b + c + e + f + h + g;
        String p44 = b + c + e + f + h;

        String p45 = a + d + e + f + h + g;
        String p46 = a + d + e + f + h;

        String p47 = b + d + e + f + h + g;
        String p48 = b + d + e + f + h;

        String p49 = a + b + f + h + g;
        String p50 = a + b + f + h;

        String p51 = b + a + f + h + g;
        String p52 = b + a + f + h;

        String p53 = a + c + f + h + g;
        String p54 = a + c + f + h;

        String p55 = a + d + f + h + g;
        String p56 = a + d + f + h;

        String p57 = a + e1 + f + h + g;
        String p58 = a + e1 + f + h;

        String p59 = a + e + f + h + g;
        String p60 = a + e + f + h;

        String p61 = b + c + f + h + g;
        String p62 = b + c + f + h;

        String p63 = b + d + f + h + g;
        String p64 = b + d + f + h;

        String p65 = b + e1 + f + h + g;
        String p66 = b + e1 + f + h;

        String p67 = b + e + f + h + g;
        String p68 = b + e + f + h;

        String p69 = c + e1 + f + h + g;
        String p70 = c + e1 + f + h;

        String p71 = d + e1 + f + h + g;
        String p72 = d + e1 + f + h;

        String p73 = c + e + f + h + g;
        String p74 = c + e + f + h;

        String p75 = d + e + f + h + g;
        String p76 = d + e + f + h;

        String p77 = e1 + f + h + g;
        String p78 = e1 + f + h;

        String p79 = c + f + h + g;
        String p80 = c + f + h;

        String p81 = d + f + h + g;
        String p82 = d + f + h;

        String p83 = e + f + h + g;
        String p84 = e + f + h;

        String p85 = a + f + h + g;
        String p86 = a + f + h;

        String p87 = b + f + h + g;
        String p88 = b + f + h;

        String p89 = f + h + g;
        String p90 = f + h;
        String p91 = f;
        //st = stcpy;
        st = identify(p1, st, n);
        //System.out.println("\nremaining string:"+st+"\n");
        st = identify(p2, st, n);
        //System.out.println("\nremaining string:"+st+"\n");
        st = identify(p3, st, n);
        st = identify(p4, st, n);
        st = identify(p5, st, n);
        st = identify(p6, st, n);
        st = identify(p7, st, n);
        st = identify(p8, st, n);
        st = identify(p9, st, n);
        st = identify(p10, st, n);
        st = identify(p11, st, n);
        st = identify(p12, st, n);
        st = identify(p13, st, n);
        st = identify(p14, st, n);
        st = identify(p15, st, n);
        st = identify(p16, st, n);
        st = identify(p17, st, n);
        st = identify(p18, st, n);
        st = identify(p19, st, n);
        st = identify(p20, st, n);
        st = identify(p21, st, n);
        st = identify(p22, st, n);
        st = identify(p23, st, n);
        st = identify(p24, st, n);
        st = identify(p25, st, n);
        st = identify(p26, st, n);
        st = identify(p27, st, n);
        st = identify(p28, st, n);
        st = identify(p29, st, n);
        st = identify(p30, st, n);
        st = identify(p31, st, n);
        st = identify(p32, st, n);
        st = identify(p33, st, n);
        st = identify(p34, st, n);
        st = identify(p35, st, n);
        st = identify(p36, st, n);
        st = identify(p37, st, n);
        st = identify(p38, st, n);
        st = identify(p39, st, n);
        st = identify(p40, st, n);
        st = identify(p41, st, n);
        st = identify(p41, st, n);
        st = identify(p42, st, n);
        st = identify(p43, st, n);
        st = identify(p44, st, n);
        st = identify(p45, st, n);
        st = identify(p46, st, n);
        st = identify(p47, st, n);
        st = identify(p48, st, n);
        st = identify(p49, st, n);
        st = identify(p50, st, n);
        st = identify(p51, st, n);
        st = identify(p52, st, n);
        st = identify(p53, st, n);
        st = identify(p54, st, n);
        st = identify(p55, st, n);
        st = identify(p56, st, n);
        st = identify(p57, st, n);
        st = identify(p58, st, n);
        st = identify(p59, st, n);
        st = identify(p60, st, n);
        st = identify(p61, st, n);
        st = identify(p62, st, n);
        st = identify(p63, st, n);
        st = identify(p64, st, n);
        st = identify(p65, st, n);
        st = identify(p66, st, n);
        st = identify(p67, st, n);
        st = identify(p68, st, n);
        st = identify(p69, st, n);
        st = identify(p70, st, n);
        st = identify(p71, st, n);
        st = identify(p72, st, n);
        st = identify(p73, st, n);
        st = identify(p74, st, n);
        st = identify(p75, st, n);
        st = identify(p76, st, n);
        st = identify(p77, st, n);
        st = identify(p78, st, n);
        st = identify(p79, st, n);
        // System.out.println("\nremaining string:"+st+"\n");
        st = identify(p80, st, n);
        //System.out.println("\nremaining string:"+st+"\n");
        st = identify(p81, st, n);
        // System.out.println("\nremaining string:"+st+"\n");
        st = identify(p82, st, n);
        // System.out.println("\nremaining string:"+st+"\n");
        st = identify(p83, st, n);
        st = identify(p84, st, n);
        st = identify(p85, st, n);
        st = identify(p86, st, n);
        st = identify(p87, st, n);
        st = identify(p88, st, n);
        st = identify(p89, st, n);
        st = identify(p90, st, n);
        st = identify(p91, st, n);
        return st;
    }//for

    public static String identify(String p1, String st, char ch) {

        //System.out.println(p1);
        //System.out.println(postag);
        getAllMatches(postag, p1);
        //System.out.println(size+"is this.");
        if (size > 0) {
            String[] splitst = st.split(" ");
            String[] split = splitst.clone();

            // System.out.println(Arrays.toString(splitst));
            // System.out.println();
            st.concat(" $");
            int l = splitst.length;
            //System.out.println("splitst");
            for (int i = 0; i < (l); i++) {
                // System.out.println(splitst[i]);
            }//for

            //System.out.println("\n\n");
            //System.out.println("\n\nSplitted string in words and tags is:");
            postag = "";
            for (int i = 0; i < (l); i++) {
                String[] splitsubst = splitst[i].split("/");
                // System.out.println(splitsubst[0]);
                //  System.out.println(splitsubst[1]);
                if (splitsubst.length > 2) {
                    splitsubst[0] = "/";
                    splitsubst[1] = splitsubst[2];
                }
                splitst[i] = splitsubst[1];
                if ((splitsubst[0].equals("-"))) {
                    splitsubst[1] = "PUN";
                    splitst[i] = "PUN";
                }
                if (splitsubst[0].equals(".")) {
                    splitsubst[1] = "DOT";
                    splitst[i] = "DOT";
                }
                if ((splitsubst[0].equals("'")) || (splitsubst[0].equals("\"")) || (splitsubst[0].equals("`"))) {
                    splitsubst[1] = "BRAC";
                    splitst[i] = "BRAC";
                }
                if (((splitsubst[0].equals("बाद")) || (splitsubst[0].equals("बीच")) || (splitsubst[0].equals("ऊपर")) || (splitsubst[0].equals("मध्य")) || (splitsubst[0].equals("नीचे")) || (splitsubst[0].equals("पीछे")) || (splitsubst[0].equals("पास"))) && (splitsubst[1].equals("NST"))) {
                    splitsubst[1] = "LOC";
                    splitst[i] = "LOC";
                }
                if ((splitsubst[0].equals("(")) || (splitsubst[0].equals("{")) || (splitsubst[0].equals("["))) {
                    splitsubst[1] = "OPEN";
                    splitst[i] = "OPEN";
                }
                if ((splitsubst[0].equals(","))) {
                    splitsubst[1] = "COMMA";
                    splitst[i] = "COMMA";
                }
                if ((splitsubst[0].equals("से"))) {
                    splitsubst[1] = "SE";
                    splitst[i] = "SE";
                }
                if ((splitsubst[0].equals(")")) || (splitsubst[0].equals("}")) || (splitsubst[0].equals("]"))) {
                    splitsubst[1] = "CLOSE";
                    splitst[i] = "CLOSE";
                }
                if (splitst[i].equals("P")) {
                    splitst[i] = "PR";
                }
                postag += splitst[i] + " ";

            }//for

            //System.out.println("\n\n"+postag);
            for (int i = 0; i < l; i++) {
                //  System.out.println(splitst[i]);
            }//for
            //System.out.println("postag=" + postag);

            // System.out.println("\n\n");
            List<String> array = getAllMatches(postag, p1);
            //System.out.println("first element"+array.get(0).length());

            //System.out.println(getAllMatches(postag, p1));
            //System.out.println(positions);
            //System.out.println("\n\n");
            //System.out.println(size);
            // System.out.println();
            for (int i = 0; i < size; i++) {
                if (!array.get(i).equals(" ")) {
                    int cmp = -1;
                    //System.out.println(positions.get(i));
                    //System.out.println("len:"+count(getAllMatches(postag, p1).get(i)));
                    for (int j = 0; j < l; j++) {
                        cmp += splitst[j].length() + 1;
                        if (cmp < positions.get(i)) {
                            continue;
                        }//if
                        else {
                            String st1 = "";
                            //System.out.println("length of p1 "+count(p1));
                            for (int k = 0; k < count(getAllMatches(postag, p1).get(i)); k++) {
                                //System.out.println("spltiing"+split[j+k]);
                                st1 += split[j + k] + " ";
                                //st=st.replaceFirst(split[j+k]+" ", "");

                                split[j + k] = "*/ZERO";
                                st = "";
                                for (int g = 0; g < l; g++) {
                                    //if (!split[g].equals("")) {
                                    st += split[g] + " ";
                                    //}
                                }
                                // System.out.println("rem st:"+st);
                            }
                            st1 = st1.replace("*/ZERO ", "");
                            if ((st1.contains("/") && !(st1.contains("/ZERO")))) {

                                if (ch == 'n') {
                                    noun.add(st1);
                                    System.out.println( "\nnoun group:" + "\033[0m" + "   " + RED_UNDERLINED + st1 + "\033[0m" + "\n");
                                } else if (ch == 'v') {
                                    verb.add(st1);
                                    System.out.println( "\nverb group:" + "\033[0m" + "   " + BLUE_UNDERLINED + st1 + "\033[0m" + "\n");
                                } else if (ch == 'j') {
                                    adjective.add(st1);
                                    System.out.println( "\nadjective group:" + "\033[0m" + "   " + PURPLE_UNDERLINED + st1 + "\033[0m" + "\n");
                                } else if (ch == 'a') {
                                    adverb.add(st1);
                                    System.out.println( "\nadverb group:" + "\033[0m" + "   " + CYAN_UNDERLINED + st1 + "\033[0m" + "\n");
                                } else if (ch == 'c') {
                                    conjunctions.add(st1);
                                    System.out.println( "\nconjunction group:" + "\033[0m" + "   " + PURPLE_UNDERLINED + st1 + "\033[0m" + "\n");
                                } else if (ch == 'q') {
                                    quantifier.add(st1);
                                    System.out.println( "\nquantifier group:" + "\033[0m" + "   " + GREEN_UNDERLINED + st1 + "\033[0m" + "\n");
                                } else if (ch == 'g') {
                                    negation.add(st1);
                                    System.out.println( "\nnegative group:" + "\033[0m" + "   " + GREEN_UNDERLINED + st1 + "\033[0m" + "\n");
                                } else if (ch == 'r') {
                                    particle.add(st1);
                                    System.out.println( "\nparticle group:" + "\033[0m" + "   " + GREEN_UNDERLINED + st1 + "\033[0m" + "\n");
                                } else if (ch == 'p') {
                                    postposition.add(st1);
                                    System.out.println( "\npostposition group:" + "\033[0m" + "   " + GREEN_UNDERLINED + st1 + "\033[0m" + "\n");
                                } else if (ch == 'u') {
                                    punctuation.add(st1);
                                    System.out.println( "\npunctuation group:" + "\033[0m" + "   " + PURPLE_UNDERLINED + st1 + "\033[0m" + "\n");
                                } else if (ch == 's') {
                                    symbol.add(st1);
                                    System.out.println( "\nsymbol group:" + "\033[0m" + "   " + GREEN_UNDERLINED + st1 + "\033[0m" + "\n");
                                }
                            }

                            break;

                        }//else
                    }//for
                }//if
            }//for
        }//if
        cnt = cnt + 1;
        //System.out.println(cnt+"st is "+st);
        return st;
    }//function

    public static int count(String st) {
        int count = st.split(" ").length;
        return count;
    }

    public static List<String> getAllMatches(String text, String regex) {
        List<String> matches = new ArrayList<String>();
        positions.clear();

        Matcher m = Pattern.compile("(?=(" + regex + "))").matcher(text);
        while (m.find()) {
            positions.add(m.start());
            matches.add(m.group(1));

        }
        size = matches.size();

        return matches;
    }

    private static String verb(String st) {
        String splitst[] = st.split(" ");
        String[] split = splitst.clone();
        //System.out.println(stcpy);
        // System.out.println(Arrays.toString(splitst));
        //System.out.println();
        //st.concat(" $");
        int l = splitst.length;

        for (int i = 0; i < (l); i++) {
            // System.out.println(splitst[i]);

        }

        //System.out.println("\n\n");
        //System.out.println("\n\nSplitted string in words and tags is:");
        for (int i = 0; i < (l); i++) {
            String[] splitsubst = splitst[i].split("/");
            // System.out.println(splitsubst[0]);
            // System.out.println(splitsubst[1]);
            if (splitsubst.length > 2) {
                splitsubst[0] = "/";
                splitsubst[1] = splitsubst[2];
            }
            splitst[i] = splitsubst[1];
            if ((splitsubst[0].equals("-"))) {
                splitsubst[1] = "PUN";
                splitst[i] = "PUN";
            }
            if ((splitsubst[0].equals("बाद"))) {
                splitsubst[1] = "LOC";
                splitst[i] = "LOC";
            }
            if ((splitsubst[0].equals(","))) {
                splitsubst[1] = "COMMA";
                splitst[i] = "COMMA";
            }
            if (splitsubst[0].equals(".")) {
                splitsubst[1] = "DOT";
                splitst[i] = "DOT";
            }
            if ((splitsubst[0].equals("'")) || (splitsubst[0].equals("\"")) || (splitsubst[0].equals("`"))) {
                splitsubst[1] = "BRAC";
                splitst[i] = "BRAC";
            }
            if ((splitsubst[0].equals("(")) || (splitsubst[0].equals("{")) || (splitsubst[0].equals("["))) {
                splitsubst[1] = "OPEN";
                splitst[i] = "OPEN";
            }
            if ((splitsubst[0].equals(")")) || (splitsubst[0].equals("}")) || (splitsubst[0].equals("]"))) {
                splitsubst[1] = "CLOSE";
                splitst[i] = "CLOSE";
            }
            if ((splitsubst[0].equals("से"))) {
                splitsubst[1] = "SE";
                splitst[i] = "SE";
            }
            if (splitst[i].equals("P")) {
                splitst[i] = "PR";
            }
            postag += splitst[i] + " ";
            // System.out.println(postag);
        }

        //System.out.println("\n\n");
        for (int i = 0; i < l; i++) {
            //System.out.println(splitst[i]);
        }
        //System.out.println("postag=" + postag);

        String quant = "((NEG )*(INTF )*(((QTC |QTF |QTO )+(SYM )*(PSP |SE |PUN |CCD )(QTC |QTF |QTO )+)|QTF |QTC |QTO )+(PSP |SE )*(SYM )*(PSP |SE )*(RPD )*(NEG )*)";
        String verb = "((VM PUN ECH )|(VM (PUN |NEG )VM (PUN |NEG )VM )|(VM (PUN |NEG )VM )|(VM ))+";
        String v = "((" + quant + verb + quant + "!(JJ ))|(" + quant + verb + ")|(" + verb + quant + "!(JJ ))|(" + verb + "))";

        String v1 = "(((DMD |DMR |DMI |DMQ )(CCD )*(PSP |SE )*(RPD )*|(RB (PSP |SE )*))*(NEG )*" + v + "(PSP |SE )*(NEG )*(RPD )*(VAUX )+(NEG )*(PSP |SE )*(RPD )*(NEG )*)";
        String v2 = "(((DMD |DMR |DMI |DMQ )(CCD )*(PSP |SE )*(RPD )*|(RB (PSP |SE )*))*(NEG )*" + v + "(PSP |SE )*(RPD )*(PSP |SE )*(NEG )*)";
        String v3 = "((DMD |DMR DMI |DMQ )(CCD )*(PSP |SE )*(RPD )*|(RB (PSP |SE )*))*(NEG )*" + quant + "(VAUX )+(NEG )*(RPD )*(PSP |SE )*(NEG )*";
        String v4 = "((DMD |DMR DMI |DMQ )(CCD )*(PSP |SE )*(RPD )*|(RB (PSP |SE )*))*(NEG )*(NEG )*(VAUX )+(NEG )*(RPD )*(PSP |SE )*(NEG )*";

        st = identify(v1, st, vb);
        // System.out.println("\nremaining string:"+st+"\n");
        //  postag = pos(st);
        st = identify(v2, st, vb);
        //  System.out.println("\nremaining string:"+st+"\n");
        //  postag = pos(st);
        st = identify(v3, st, vb);
        //  postag = pos(st);
        st = identify(v4, st, vb);
        return st;

    }

    private static String adjective(String st) {
        String splitst[] = st.split(" ");
        String[] split = splitst.clone();
        //System.out.println(stcpy);
        // System.out.println(Arrays.toString(splitst));
      //  System.out.println();
        //st.concat(" $");
        int l = splitst.length;

        for (int i = 0; i < (l); i++) {
            // System.out.println(splitst[i]);

        }

        //System.out.println("\n\n");
        //System.out.println("\n\nSplitted string in words and tags is:");
        for (int i = 0; i < (l); i++) {
            String[] splitsubst = splitst[i].split("/");
            // System.out.println(splitsubst[0]);
            // System.out.println(splitsubst[1]);
            if (splitsubst.length > 2) {
                splitsubst[0] = "/";
                splitsubst[1] = splitsubst[2];
            }
            splitst[i] = splitsubst[1];
            if ((splitsubst[0].equals("-"))) {
                splitsubst[1] = "PUN";
                splitst[i] = "PUN";
            }
            if ((splitsubst[0].equals("बाद"))) {
                splitsubst[1] = "LOC";
                splitst[i] = "LOC";
            }
            if ((splitsubst[0].equals(","))) {
                splitsubst[1] = "COMMA";
                splitst[i] = "COMMA";
            }
            if (splitsubst[0].equals(".")) {
                splitsubst[1] = "DOT";
                splitst[i] = "DOT";
            }
            if ((splitsubst[0].equals("'")) || (splitsubst[0].equals("\"")) || (splitsubst[0].equals("`"))) {
                splitsubst[1] = "BRAC";
                splitst[i] = "BRAC";
            }
            if ((splitsubst[0].equals("(")) || (splitsubst[0].equals("{")) || (splitsubst[0].equals("["))) {
                splitsubst[1] = "OPEN";
                splitst[i] = "OPEN";
            }
            if ((splitsubst[0].equals(")")) || (splitsubst[0].equals("}")) || (splitsubst[0].equals("]"))) {
                splitsubst[1] = "CLOSE";
                splitst[i] = "CLOSE";
            }
            if ((splitsubst[0].equals("से"))) {
                splitsubst[1] = "SE";
                splitst[i] = "SE";
            }
            if (splitst[i].equals("P")) {
                splitst[i] = "PR";
            }
            postag += splitst[i] + " ";
            // System.out.println(postag);
        }

        //System.out.println("\n\n");
        String adj1 = "((NEG )*(((QTC |QTF |QTO )+(SYM )*(NEG )*(PSP |SE |PUN |CCD |DOT )(NEG )*(QTC |QTF |QTO )+)|(QTC |QTF |QTO |INTF )+)*(DMD |DMR |DMI |DMQ )*(RPD )*(JJ )+(RPD )*(PSP |SE )*(NEG )*)";
        //   postag = pos(st);
        st = identify(adj1, st, ad);
        return st;

    }

    private static String adverb(String st) {
        String splitst[] = st.split(" ");
        String[] split = splitst.clone();
        //System.out.println(stcpy);
        // System.out.println(Arrays.toString(splitst));
       // System.out.println();
        //st.concat(" $");
        int l = splitst.length;

        for (int i = 0; i < (l); i++) {
            // System.out.println(splitst[i]);

        }

        //System.out.println("\n\n");
        //System.out.println("\n\nSplitted string in words and tags is:");
        for (int i = 0; i < (l); i++) {
            String[] splitsubst = splitst[i].split("/");
            // System.out.println(splitsubst[0]);
            // System.out.println(splitsubst[1]);
            if (splitsubst.length > 2) {
                splitsubst[0] = "/";
                splitsubst[1] = splitsubst[2];
            }
            splitst[i] = splitsubst[1];
            if ((splitsubst[0].equals("-"))) {
                splitsubst[1] = "PUN";
                splitst[i] = "PUN";
            }
            if ((splitsubst[0].equals("बाद"))) {
                splitsubst[1] = "LOC";
                splitst[i] = "LOC";
            }
            if ((splitsubst[0].equals(","))) {
                splitsubst[1] = "COMMA";
                splitst[i] = "COMMA";
            }
            if (splitsubst[0].equals(".")) {
                splitsubst[1] = "DOT";
                splitst[i] = "DOT";
            }
            if ((splitsubst[0].equals("'")) || (splitsubst[0].equals("\"")) || (splitsubst[0].equals("`"))) {
                splitsubst[1] = "BRAC";
                splitst[i] = "BRAC";
            }
            if ((splitsubst[0].equals("(")) || (splitsubst[0].equals("{")) || (splitsubst[0].equals("["))) {
                splitsubst[1] = "OPEN";
                splitst[i] = "OPEN";
            }
            if ((splitsubst[0].equals(")")) || (splitsubst[0].equals("}")) || (splitsubst[0].equals("]"))) {
                splitsubst[1] = "CLOSE";
                splitst[i] = "CLOSE";
            }
            if ((splitsubst[0].equals("से"))) {
                splitsubst[1] = "SE";
                splitst[i] = "SE";
            }
            if (splitst[i].equals("P")) {
                splitst[i] = "PR";
            }
            postag += splitst[i] + " ";
            // System.out.println(postag);
        }

        //System.out.println("\n\n");
        String adv1 = "((RB )+(RPD )*(PSP |SE )*)";
        //   postag = pos(st);
        st = identify(adv1, st, adve);
        return st;
    }

    private static String conjunction(String st) {
        String splitst[] = st.split(" ");
        String[] split = splitst.clone();
        //System.out.println(stcpy);
        // System.out.println(Arrays.toString(splitst));
      //  System.out.println();
        //st.concat(" $");
        int l = splitst.length;

        for (int i = 0; i < (l); i++) {
            // System.out.println(splitst[i]);

        }

        //System.out.println("\n\n");
        //System.out.println("\n\nSplitted string in words and tags is:");
        for (int i = 0; i < (l); i++) {
            String[] splitsubst = splitst[i].split("/");
            // System.out.println(splitsubst[0]);
            // System.out.println(splitsubst[1]);
            if (splitsubst.length > 2) {
                splitsubst[0] = "/";
                splitsubst[1] = splitsubst[2];
            }
            splitst[i] = splitsubst[1];
            if ((splitsubst[0].equals("-"))) {
                splitsubst[1] = "PUN";
                splitst[i] = "PUN";
            }
            if ((splitsubst[0].equals("बाद"))) {
                splitsubst[1] = "LOC";
                splitst[i] = "LOC";
            }
            if ((splitsubst[0].equals(","))) {
                splitsubst[1] = "COMMA";
                splitst[i] = "COMMA";
            }
            if (splitsubst[0].equals(".")) {
                splitsubst[1] = "DOT";
                splitst[i] = "DOT";
            }
            if ((splitsubst[0].equals("'")) || (splitsubst[0].equals("\"")) || (splitsubst[0].equals("`"))) {
                splitsubst[1] = "BRAC";
                splitst[i] = "BRAC";
            }
            if ((splitsubst[0].equals("(")) || (splitsubst[0].equals("{")) || (splitsubst[0].equals("["))) {
                splitsubst[1] = "OPEN";
                splitst[i] = "OPEN";
            }
            if ((splitsubst[0].equals(")")) || (splitsubst[0].equals("}")) || (splitsubst[0].equals("]"))) {
                splitsubst[1] = "CLOSE";
                splitst[i] = "CLOSE";
            }
            if ((splitsubst[0].equals("से"))) {
                splitsubst[1] = "SE";
                splitst[i] = "SE";
            }
            if (splitst[i].equals("P")) {
                splitst[i] = "PR";
            }
            postag += splitst[i] + " ";
            // System.out.println(postag);
        }

        //System.out.println("\n\n");
        String c1 = "(DMD |DMR |DMI |DMQ )*(CCD |CCS )+(RPD )*";
        //  postag = pos(st);
        st = identify(c1, st, con);
        return st;
    }

    private static String quantifier(String st) {
        String splitst[] = st.split(" ");
        String[] split = splitst.clone();
        //System.out.println(stcpy);
        // System.out.println(Arrays.toString(splitst));
       // System.out.println();
        //st.concat(" $");
        int l = splitst.length;

        for (int i = 0; i < (l); i++) {
            // System.out.println(splitst[i]);

        }

        //System.out.println("\n\n");
        //System.out.println("\n\nSplitted string in words and tags is:");
        for (int i = 0; i < (l); i++) {
            String[] splitsubst = splitst[i].split("/");
            // System.out.println(splitsubst[0]);
            // System.out.println(splitsubst[1]);
            if (splitsubst.length > 2) {
                splitsubst[0] = "/";
                splitsubst[1] = splitsubst[2];
            }
            splitst[i] = splitsubst[1];
            if ((splitsubst[0].equals("-"))) {
                splitsubst[1] = "PUN";
                splitst[i] = "PUN";
            }
            if ((splitsubst[0].equals("बाद")) || (splitsubst[0].equals("बीच"))) {
                splitsubst[1] = "LOC";
                splitst[i] = "LOC";
            }
            if ((splitsubst[0].equals(","))) {
                splitsubst[1] = "COMMA";
                splitst[i] = "COMMA";
            }
            if (splitsubst[0].equals(".")) {
                splitsubst[1] = "DOT";
                splitst[i] = "DOT";
            }
            if ((splitsubst[0].equals("'")) || (splitsubst[0].equals("\"")) || (splitsubst[0].equals("`"))) {
                splitsubst[1] = "BRAC";
                splitst[i] = "BRAC";
            }
            if ((splitsubst[0].equals("(")) || (splitsubst[0].equals("{")) || (splitsubst[0].equals("["))) {
                splitsubst[1] = "OPEN";
                splitst[i] = "OPEN";
            }
            if ((splitsubst[0].equals(")")) || (splitsubst[0].equals("}")) || (splitsubst[0].equals("]"))) {
                splitsubst[1] = "CLOSE";
                splitst[i] = "CLOSE";
            }
            if ((splitsubst[0].equals("से"))) {
                splitsubst[1] = "SE";
                splitst[i] = "SE";
            }
            if (splitst[i].equals("P")) {
                splitst[i] = "PR";
            }
            postag += splitst[i] + " ";
            // System.out.println(postag);
        }

        //System.out.println("\n\n");
        String qt = "((NEG )*(DMD |DMR |DMQ |DMI )*(PSP |SE )*(((QTC |QTF |QTO )+(SYM )*(NEG )*(PSP |SE |PUN |CCD |DOT )(NEG )*(QTC |QTF |QTO )+)|(QTC |QTF |QTO )+)(RPD )*(PSP |SE )*)";
        //  postag = pos(st);
        st = identify(qt, st, qnt);
        return st;
    }

    private static String negation(String st) {
        String splitst[] = st.split(" ");
        String[] split = splitst.clone();
        //System.out.println(stcpy);
        // System.out.println(Arrays.toString(splitst));
        //System.out.println();
        //st.concat(" $");
        int l = splitst.length;

        for (int i = 0; i < (l); i++) {
            // System.out.println(splitst[i]);

        }

        //System.out.println("\n\n");
        //System.out.println("\n\nSplitted string in words and tags is:");
        for (int i = 0; i < (l); i++) {
            String[] splitsubst = splitst[i].split("/");
            // System.out.println(splitsubst[0]);
            // System.out.println(splitsubst[1]);
            if (splitsubst.length > 2) {
                splitsubst[0] = "/";
                splitsubst[1] = splitsubst[2];
            }
            splitst[i] = splitsubst[1];
            if ((splitsubst[0].equals("-"))) {
                splitsubst[1] = "PUN";
                splitst[i] = "PUN";
            }
            if ((splitsubst[0].equals("बाद"))) {
                splitsubst[1] = "LOC";
                splitst[i] = "LOC";
            }
            if ((splitsubst[0].equals(","))) {
                splitsubst[1] = "COMMA";
                splitst[i] = "COMMA";
            }
            if (splitsubst[0].equals(".")) {
                splitsubst[1] = "DOT";
                splitst[i] = "DOT";
            }
            if ((splitsubst[0].equals("'")) || (splitsubst[0].equals("\"")) || (splitsubst[0].equals("`"))) {
                splitsubst[1] = "BRAC";
                splitst[i] = "BRAC";
            }
            if ((splitsubst[0].equals("(")) || (splitsubst[0].equals("{")) || (splitsubst[0].equals("["))) {
                splitsubst[1] = "OPEN";
                splitst[i] = "OPEN";
            }
            if ((splitsubst[0].equals(")")) || (splitsubst[0].equals("}")) || (splitsubst[0].equals("]"))) {
                splitsubst[1] = "CLOSE";
                splitst[i] = "CLOSE";
            }
            if ((splitsubst[0].equals("से"))) {
                splitsubst[1] = "SE";
                splitst[i] = "SE";
            }
            if (splitst[i].equals("P")) {
                splitst[i] = "PR";
            }
            postag += splitst[i] + " ";
            // System.out.println(postag);
        }

        //System.out.println("\n\n");
        String neg1 = "((DMD |DMR |DMI |DMQ )*(NEG )+)";
        //  postag = pos(st);
        st = identify(neg1, st, ne);
        return st;
    }

    private static String particle(String st) {
        String splitst[] = st.split(" ");
        String[] split = splitst.clone();
        //System.out.println(stcpy);
        // System.out.println(Arrays.toString(splitst));
      //  System.out.println();
        //st.concat(" $");
        int l = splitst.length;

        for (int i = 0; i < (l); i++) {
            // System.out.println(splitst[i]);

        }

        //System.out.println("\n\n");
        //System.out.println("\n\nSplitted string in words and tags is:");
        for (int i = 0; i < (l); i++) {
            String[] splitsubst = splitst[i].split("/");
            // System.out.println(splitsubst[0]);
            // System.out.println(splitsubst[1]);
            if (splitsubst.length > 2) {
                splitsubst[0] = "/";
                splitsubst[1] = splitsubst[2];
            }
            splitst[i] = splitsubst[1];
            if ((splitsubst[0].equals("-"))) {
                splitsubst[1] = "PUN";
                splitst[i] = "PUN";
            }
            if ((splitsubst[0].equals("बाद"))) {
                splitsubst[1] = "LOC";
                splitst[i] = "LOC";
            }
            if ((splitsubst[0].equals(","))) {
                splitsubst[1] = "COMMA";
                splitst[i] = "COMMA";
            }
            if (splitsubst[0].equals(".")) {
                splitsubst[1] = "DOT";
                splitst[i] = "DOT";
            }
            if ((splitsubst[0].equals("'")) || (splitsubst[0].equals("\"")) || (splitsubst[0].equals("`"))) {
                splitsubst[1] = "BRAC";
                splitst[i] = "BRAC";
            }
            if ((splitsubst[0].equals("(")) || (splitsubst[0].equals("{")) || (splitsubst[0].equals("["))) {
                splitsubst[1] = "OPEN";
                splitst[i] = "OPEN";
            }
            if ((splitsubst[0].equals(")")) || (splitsubst[0].equals("}")) || (splitsubst[0].equals("]"))) {
                splitsubst[1] = "CLOSE";
                splitst[i] = "CLOSE";
            }
            if ((splitsubst[0].equals("से"))) {
                splitsubst[1] = "SE";
                splitst[i] = "SE";
            }
            if (splitst[i].equals("P")) {
                splitst[i] = "PR";
            }
            postag += splitst[i] + " ";
            // System.out.println(postag);
        }

        //System.out.println("\n\n");
        String rpd = "((DMD |DMR |DMI |DMQ )*(RPD )+)";
        // postag = pos(st);
        st = identify(rpd, st, part);
        return st;
    }

    private static String postposition(String st) {
        String splitst[] = st.split(" ");
        String[] split = splitst.clone();
        //System.out.println(stcpy);
        // System.out.println(Arrays.toString(splitst));
       // System.out.println();
        //st.concat(" $");
        int l = splitst.length;

        for (int i = 0; i < (l); i++) {
            // System.out.println(splitst[i]);

        }

        //System.out.println("\n\n");
        //System.out.println("\n\nSplitted string in words and tags is:");
        for (int i = 0; i < (l); i++) {
            String[] splitsubst = splitst[i].split("/");
            // System.out.println(splitsubst[0]);
            // System.out.println(splitsubst[1]);
            if (splitsubst.length > 2) {
                splitsubst[0] = "/";
                splitsubst[1] = splitsubst[2];
            }
            splitst[i] = splitsubst[1];
            if ((splitsubst[0].equals("-"))) {
                splitsubst[1] = "PUN";
                splitst[i] = "PUN";
            }
            if ((splitsubst[0].equals("बाद"))) {
                splitsubst[1] = "LOC";
                splitst[i] = "LOC";
            }
            if ((splitsubst[0].equals(","))) {
                splitsubst[1] = "COMMA";
                splitst[i] = "COMMA";
            }
            if (splitsubst[0].equals(".")) {
                splitsubst[1] = "DOT";
                splitst[i] = "DOT";
            }
            if ((splitsubst[0].equals("'")) || (splitsubst[0].equals("\"")) || (splitsubst[0].equals("`"))) {
                splitsubst[1] = "BRAC";
                splitst[i] = "BRAC";
            }
            if ((splitsubst[0].equals("(")) || (splitsubst[0].equals("{")) || (splitsubst[0].equals("["))) {
                splitsubst[1] = "OPEN";
                splitst[i] = "OPEN";
            }
            if ((splitsubst[0].equals(")")) || (splitsubst[0].equals("}")) || (splitsubst[0].equals("]"))) {
                splitsubst[1] = "CLOSE";
                splitst[i] = "CLOSE";
            }
            if ((splitsubst[0].equals("से"))) {
                splitsubst[1] = "SE";
                splitst[i] = "SE";
            }
            if (splitst[i].equals("P")) {
                splitst[i] = "PR";
            }
            postag += splitst[i] + " ";
            // System.out.println(postag);
        }

        //System.out.println("\n\n");
        String psp = "((PSP |SE )+)";
        //postag = pos(st);
        st = identify(psp, st, pp);
        return st;
    }

    private static String symbol(String st) {
        String splitst[] = st.split(" ");
        String[] split = splitst.clone();
        //System.out.println(stcpy);
        // System.out.println(Arrays.toString(splitst));
      //  System.out.println();
        //st.concat(" $");
        int l = splitst.length;

        for (int i = 0; i < (l); i++) {
            // System.out.println(splitst[i]);

        }

        //System.out.println("\n\n");
        //System.out.println("\n\nSplitted string in words and tags is:");
        for (int i = 0; i < (l); i++) {
            String[] splitsubst = splitst[i].split("/");
            // System.out.println(splitsubst[0]);
            // System.out.println(splitsubst[1]);
            if (splitsubst.length > 2) {
                splitsubst[0] = "/";
                splitsubst[1] = splitsubst[2];
            }
            splitst[i] = splitsubst[1];
            if ((splitsubst[0].equals("-"))) {
                splitsubst[1] = "PUN";
                splitst[i] = "PUN";
            }
            if ((splitsubst[0].equals("बाद"))) {
                splitsubst[1] = "LOC";
                splitst[i] = "LOC";
            }
            if ((splitsubst[0].equals(","))) {
                splitsubst[1] = "COMMA";
                splitst[i] = "COMMA";
            }
            if (splitsubst[0].equals(".")) {
                splitsubst[1] = "DOT";
                splitst[i] = "DOT";
            }
            if ((splitsubst[0].equals("'")) || (splitsubst[0].equals("\"")) || (splitsubst[0].equals("`"))) {
                splitsubst[1] = "BRAC";
                splitst[i] = "BRAC";
            }
            if ((splitsubst[0].equals("(")) || (splitsubst[0].equals("{")) || (splitsubst[0].equals("["))) {
                splitsubst[1] = "OPEN";
                splitst[i] = "OPEN";
            }
            if ((splitsubst[0].equals(")")) || (splitsubst[0].equals("}")) || (splitsubst[0].equals("]"))) {
                splitsubst[1] = "CLOSE";
                splitst[i] = "CLOSE";
            }
            if ((splitsubst[0].equals("से"))) {
                splitsubst[1] = "SE";
                splitst[i] = "SE";
            }
            if (splitst[i].equals("P")) {
                splitst[i] = "PR";
            }
            postag += splitst[i] + " ";
            // System.out.println(postag);
        }

        //System.out.println("\n\n");
        String sym = "(SYM |OPEN |CLOSE )+";
        //postag = pos(st);
        st = identify(sym, st, sy);
        return st;
    }

    private static String punctuation(String st) {
        String splitst[] = st.split(" ");
        String[] split = splitst.clone();
        //System.out.println(stcpy);
        // System.out.println(Arrays.toString(splitst));
      //  System.out.println();
        //st.concat(" $");
        int l = splitst.length;

        for (int i = 0; i < (l); i++) {
            // System.out.println(splitst[i]);

        }

        //System.out.println("\n\n");
        //System.out.println("\n\nSplitted string in words and tags is:");
        for (int i = 0; i < (l); i++) {
            String[] splitsubst = splitst[i].split("/");
            // System.out.println(splitsubst[0]);
            // System.out.println(splitsubst[1]);
            if (splitsubst.length > 2) {
                splitsubst[0] = "/";
                splitsubst[1] = splitsubst[2];
            }
            splitst[i] = splitsubst[1];
            if ((splitsubst[0].equals("-"))) {
                splitsubst[1] = "PUN";
                splitst[i] = "PUN";
            }
            if ((splitsubst[0].equals("बाद"))) {
                splitsubst[1] = "LOC";
                splitst[i] = "LOC";
            }
            if ((splitsubst[0].equals(","))) {
                splitsubst[1] = "COMMA";
                splitst[i] = "COMMA";
            }
            if (splitsubst[0].equals(".")) {
                splitsubst[1] = "DOT";
                splitst[i] = "DOT";
            }
            if ((splitsubst[0].equals("'")) || (splitsubst[0].equals("\"")) || (splitsubst[0].equals("`"))) {
                splitsubst[1] = "BRAC";
                splitst[i] = "BRAC";
            }
            if ((splitsubst[0].equals("(")) || (splitsubst[0].equals("{")) || (splitsubst[0].equals("["))) {
                splitsubst[1] = "OPEN";
                splitst[i] = "OPEN";
            }
            if ((splitsubst[0].equals(")")) || (splitsubst[0].equals("}")) || (splitsubst[0].equals("]"))) {
                splitsubst[1] = "CLOSE";
                splitst[i] = "CLOSE";
            }
            if ((splitsubst[0].equals("से"))) {
                splitsubst[1] = "SE";
                splitst[i] = "SE";
            }
            if (splitst[i].equals("P")) {
                splitst[i] = "PR";
            }
            postag += splitst[i] + " ";
            // System.out.println(postag);
        }

        //System.out.println("\n\n");
        String n1 = "((PUNC |PUN |COMMA |BRAC |DOT )+)";
        //postag = pos(st);
        st = identify(n1, st, punt);
        return st;
    }

    private static List<String>  insertion_sort(List<Integer> posi, List<String> arr,List<String> tag) {
       // System.out.println(posi);
       // System.out.println(arr);
        int n=posi.size();
   int i, key, j;
   String skey,tkey;
   for (i = 1; i < n; i++)
   {
       key = posi.get(i);
       skey=arr.get(i);
       tkey=tag.get(i);
       j = i-1;
 
       /* Move elements of arr[0..i-1], that are
          greater than key, to one position ahead
          of their current position */
       while (j >= 0 && posi.get(j) > key)
       {
           posi.set(j+1,posi.get(j));
           arr.set(j+1, arr.get(j));
          tag.set(j+1, tag.get(j));
           j = j-1;
       }
       posi.set(j+1,key);
           arr.set(j+1, skey);
           tag.set(j+1, tkey);
   }
   String tempp=arr.get(0);
   String tempt=tag.get(0);
    for (i = 0; i < n-1; i++)
   {
       arr.set(i, arr.get(i+1));
       tag.set(i, tag.get(i+1));
   }
    arr.set(i, tempp);
    tag.set(i,tempt);
   // System.out.println(tag);
    String output=null;
    for(i=0;i<n;i++)
    {
        output=arr.get(i)+"--"+tag.get(i);
        arr.set(i, output);
    }
    //System.out.println(arr);
   return arr;
}
    
}//class end

