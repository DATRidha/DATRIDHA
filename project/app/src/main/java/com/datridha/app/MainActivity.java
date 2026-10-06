package com.datridha.app;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.view.View;
import android.widget.*;
import java.util.ArrayList;

public class MainActivity extends Activity {
    LinearLayout root, content;
    boolean french = false;
    ArrayList<String> orders = new ArrayList<>();
    int brown = Color.rgb(112, 63, 25), gold = Color.rgb(226, 169, 65), green = Color.rgb(54, 120, 62), orange = Color.rgb(207, 111, 38), blue = Color.rgb(45, 105, 170), purple = Color.rgb(112, 75, 150);

    @Override public void onCreate(Bundle b) { super.onCreate(b); showHome(); }

    TextView tv(String text, float size, int color, boolean bold) {
        TextView t=new TextView(this); t.setText(text); t.setTextSize(size); t.setTextColor(color); t.setGravity(Gravity.CENTER); t.setTypeface(null,bold?Typeface.BOLD:Typeface.NORMAL); t.setPadding(12,12,12,12); return t;
    }
    Button btn(String text,int color){ Button b=new Button(this); b.setText(text); b.setTextSize(17); b.setTextColor(Color.WHITE); b.setAllCaps(false); GradientDrawable g=new GradientDrawable(); g.setColor(color); g.setCornerRadius(28); b.setBackground(g); b.setPadding(10,8,10,8); return b; }
    LinearLayout page(){ LinearLayout l=new LinearLayout(this); l.setOrientation(LinearLayout.VERTICAL); l.setPadding(28,18,28,28); l.setGravity(Gravity.CENTER_HORIZONTAL); return l; }
    void base(){ root=new LinearLayout(this); root.setOrientation(LinearLayout.VERTICAL); root.setBackgroundColor(Color.rgb(250,246,238)); root.setPadding(18,18,18,18); setContentView(root); }
    void header(){
        LinearLayout lang=new LinearLayout(this); lang.setGravity(Gravity.CENTER); Button fr=btn("Français",brown), ar=btn("العربية",green); lang.addView(fr,new LinearLayout.LayoutParams(0,55,1)); lang.addView(ar,new LinearLayout.LayoutParams(0,55,1)); root.addView(lang);
        fr.setOnClickListener(v->{french=true;showHome();}); ar.setOnClickListener(v->{french=false;showHome();});
        TextView title=tv("DATRIDHA",32,brown,true); root.addView(title,new LinearLayout.LayoutParams(-1,70));
        root.addView(tv(french?"Dattes Deglet Nour de Bechni\nCommerce en gros":"دقلة النور من بشني\nتجارة بالجملة",18,Color.DKGRAY,true));
    }
    void showHome(){ base(); header(); content=new LinearLayout(this); content.setOrientation(LinearLayout.VERTICAL); content.setPadding(0,20,0,0); root.addView(content,new LinearLayout.LayoutParams(-1,-1));
        String buy=french?"Acheter Deglet Nour":"شراء دقلة النور", sell=french?"Vendre Deglet Nour":"بيع دقلة النور", ord=french?"Commandes":"الطلبات", contact=french?"Nous contacter":"تواصل معنا";
        Button b1=btn(buy,green),b2=btn(sell,orange),b3=btn(ord,blue),b4=btn(contact,purple); addCard(b1);addCard(b2);addCard(b3);addCard(b4);
        b1.setOnClickListener(v->showBuy()); b2.setOnClickListener(v->showSell()); b3.setOnClickListener(v->showOrders()); b4.setOnClickListener(v->showContact());
    }
    void addCard(Button b){ LinearLayout.LayoutParams p=new LinearLayout.LayoutParams(-1,72); p.setMargins(0,7,0,7); content.addView(b,p); }
    void topBack(String title){ base(); Button back=btn(french?"← Retour":"← رجوع",brown); root.addView(back,new LinearLayout.LayoutParams(-1,58)); back.setOnClickListener(v->showHome()); root.addView(tv(title,25,brown,true),new LinearLayout.LayoutParams(-1,70)); content=new LinearLayout(this); content.setOrientation(LinearLayout.VERTICAL); root.addView(content,new LinearLayout.LayoutParams(-1,-1)); }
    EditText field(String hint){ EditText e=new EditText(this); e.setHint(hint); e.setTextSize(17); e.setPadding(18,8,18,8); content.addView(e,new LinearLayout.LayoutParams(-1,60)); return e; }
    void showBuy(){ topBack(french?"Acheter Deglet Nour":"شراء دقلة النور"); content.addView(tv(french?"Saisissez la quantité souhaitée":"أدخل الكمية المطلوبة",18,Color.DKGRAY,true)); EditText q=field(french?"Quantité (kg ou tonne)":"الكمية (كغ أو طن)"); EditText price=field(french?"Prix souhaité (DT)":"السعر المطلوب (د.ت)"); Button send=btn(french?"Envoyer la demande":"إرسال طلب الشراء",green); content.addView(send,new LinearLayout.LayoutParams(-1,65)); send.setOnClickListener(v->{orders.add((french?"Achat":"شراء")+" - "+q.getText()+" - "+price.getText()+" DT"); Toast.makeText(this,french?"Demande enregistrée":"تم تسجيل طلب الشراء",Toast.LENGTH_SHORT).show(); showOrders();}); }
    void showSell(){ topBack(french?"Vendre Deglet Nour":"بيع دقلة النور"); content.addView(tv(french?"Publiez votre offre de Deglet Nour":"أدخل عرض دقلة النور للبيع",18,Color.DKGRAY,true)); EditText q=field(french?"Quantité disponible (kg ou tonne)":"الكمية المتوفرة (كغ أو طن)"); EditText price=field(french?"Prix par kg (DT)":"السعر للكيلوغرام (د.ت)"); EditText note=field(french?"Qualité / détails":"الجودة / التفاصيل"); Button send=btn(french?"Publier l'offre":"نشر عرض البيع",orange); content.addView(send,new LinearLayout.LayoutParams(-1,65)); send.setOnClickListener(v->{orders.add((french?"Vente":"بيع")+" - "+q.getText()+" - "+price.getText()+" DT"); Toast.makeText(this,french?"Offre enregistrée":"تم تسجيل عرض البيع",Toast.LENGTH_SHORT).show(); showOrders();}); }
    void showOrders(){ topBack(french?"Commandes":"الطلبات"); if(orders.size()==0){content.addView(tv(french?"Aucune commande pour le moment":"لا توجد طلبات حاليًا",19,Color.DKGRAY,false));} else {for(String s:orders){TextView t=tv("• "+s,17,Color.DKGRAY,false); t.setGravity(Gravity.START|Gravity.CENTER_VERTICAL); content.addView(t,new LinearLayout.LayoutParams(-1,60));}} }
    void showContact(){ topBack(french?"Nous contacter":"تواصل معنا"); content.addView(tv(french?"DATRIDHA – Commerce de Deglet Nour en gros":"DATRIDHA – تجارة دقلة النور بالجملة",20,brown,true)); content.addView(tv(french?"Pour toute demande concernant l'achat, la vente ou les commandes, contactez l'équipe DATRIDHA.":"للاستفسار حول الشراء أو البيع أو الطلبات، تواصل مع فريق DATRIDHA.",17,Color.DKGRAY,false)); Button msg=btn(french?"Envoyer un message":"إرسال رسالة",purple); content.addView(msg,new LinearLayout.LayoutParams(-1,65)); msg.setOnClickListener(v->Toast.makeText(this,french?"Merci de nous contacter":"شكرًا لتواصلك معنا",Toast.LENGTH_SHORT).show()); }
}
