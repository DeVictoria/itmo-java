package md2html;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Convert {
    Map<String, List<Integer>> map = new HashMap<>();
    StringBuilder s= new StringBuilder();
    int co=0;
    int i=0;
    String start = "";
    String end = "";
    Convert(){
    }
    int det=0;

    public void markup(String st, String stt, int i, int j){
        ArrayList<Integer> listt = new ArrayList<>(this.map.get(st + stt));
        if (listt.get(0) == 0) {
            listt.add(i);
            listt.add(i+j);
            listt.set(0, 1);
            this.map.put(st+stt, listt);
        }
        else if(listt.get(0) == 1) {
            this.s.replace(i, i+j, "</"+st);
            this.s.replace(listt.get(1), listt.get(2), "<"+st);
            listt.clear();
            listt.add(0);
            this.map.put(st+stt, listt);
        }
    }
    public void distribut(char c, char cc, int i){
        if (c=='*'){
            if (cc=='*') {
                this.s.append(cc);
                this.s.append(c);
                markup("strong>","**", this.det,2);
                this.i=i+1;
            }else{
                this.s.append(c);
                markup("em>","*", this.det,1);
            }
        }
        else if (c=='_'){
            if (cc=='_') {
                this.s.append(cc);
                this.s.append(c);
                markup("strong>","__", this.det,2);
                this.i=i+1;
            }else{
                this.s.append(c);
                markup("em>","_",  this.det,1);
            }
        }
        else if (c=='-'){
            if (cc=='-') {
                this.s.append(cc);
                this.s.append(c);
                markup("s>","--", this.det,2);
                this.i=i+1;
            }else{
                this.s.append("-");
            }
        }
        else if (c=='+'){
            if (cc=='+') {
                this.s.append(cc);
                this.s.append(c);
                markup("u>","++", this.det,2);
                this.i=i+1;
            }else{
                this.s.append("+");
            }
        }
        else if (c=='\\'){
            this.s.append(cc);
            this.i=i+1;
        }
        else if (c=='<'){
            this.s.append("&lt;");
        }
        else if (c=='>'){
            this.s.append("&gt;");
        }
        else if (c=='&'){
            this.s.append("&amp;");
        }
        else if (c=='`'){
            this.s.append(c);
            markup("code>","`", this.det,1);
        }
        else {
            this.s.append(c);
        }

    }
    public void convert(StringBuilder s){
        ArrayList<Integer> list = new ArrayList<>();
        list.add(0);
        this.map.put("em>"+"_", list);
        this.map.put("em>"+"*", list);
        this.map.put("strong>"+"__", list);
        this.map.put("strong>"+"**", list);
        this.map.put("s>"+"--", list);
        this.map.put("u>"+"++", list);
        this.map.put("code>"+"`", list);
        int z = 0;
        char c;
        c = s.charAt(0);
        StringBuilder lk = new StringBuilder();
        lk.append(c);
        while(c=='#'){
            z++;
            c = s.charAt(z);
            lk.append(c);
        }
        if((z>0) && (c==' ')){
            this.co=z;
            this.start = "<h" + co+">";
            this.end =  "</h" + co+">";
            this.i=z+1;
        }
        else {
            this.start = "<p>";
            this.end =  "</p>";
            this.i=0;
        }
        this.s= new StringBuilder(this.start);
        while(this.i< s.length()) {

            c = s.charAt(i);
            det= this.s.length();
            if (i< s.length()-1) {
                distribut(c, s.charAt(i + 1), i);
                i++;
            }
            else{
                distribut(c, ' ', i);
                i++;
            }
        }
        if (s.charAt(s.length()-1)=='\n'){
            this.s.replace(this.s.length()-1, this.s.length(), this.end);
        }
        else {
            this.s.append(this.end);
        }
    }
}
