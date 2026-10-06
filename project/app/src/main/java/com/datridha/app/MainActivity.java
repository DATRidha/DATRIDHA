package com.datridha.app;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.view.Gravity;
import android.view.View;
import android.widget.*;

import java.util.ArrayList;

public class MainActivity extends Activity {
    private final int GREEN = Color.rgb(23,107,58);
    private final int LIGHT_GREEN = Color.rgb(46,139,87);
    private final int GOLD = Color.rgb(212,175,55);
    private final int BROWN = Color.rgb(91,58,30);
    private final int BEIGE = Color.rgb(247,233,215);
    private final int WHITE = Color.WHITE;
    private final int GRAY = Color.rgb(105,105,105);
    private LinearLayout root;
    private String lang = "AR";

    @Override public void onCreate(Bundle b) {
        super.onCreate(b);
        showHome();
    }

    private int dp(int n) { return (int)(n * getResources().getDisplayMetrics().density + 0.5f); }
    private TextView tv(String text, float size, int color, boolean bold) {
        TextView t = new TextView(this); t.setText(text); t.setTextSize(size); t.setTextColor(color);
        t.setTypeface(Typeface.create("sans", bold ? Typeface.BOLD : Typeface.NORMAL));
        t.setGravity(Gravity.CENTER_VERTICAL); t.setPadding(dp(8),dp(5),dp(8),dp(5)); return t;
    }
    private Button btn(String text) {
        Button b = new Button(this); b.setText(text); b.setTextSize(15); b.setTextColor(WHITE);
        b.setAllCaps(false); b.setTypeface(Typeface.DEFAULT_BOLD); b.setPadding(dp(10),dp(3),dp(10),dp(3));
        b.setBackground(round(GREEN,18)); return b;
    }
    private GradientDrawable round(int color, int radius) {
        GradientDrawable g = new GradientDrawable(); g.setColor(color); g.setCornerRadius(dp(radius)); return g;
    }
    private void base(String title) {
        root = new LinearLayout(this); root.setOrientation(LinearLayout.VERTICAL); root.setBackgroundColor(BEIGE);
        ScrollView scroll = new ScrollView(this); LinearLayout content = new LinearLayout(this); content.setOrientation(LinearLayout.VERTICAL);
        content.setPadding(dp(16),dp(12),dp(16),dp(90)); scroll.addView(content); root.addView(scroll,new LinearLayout.LayoutParams(-1,0,1));
        setContentView(root); header(content,title); root.setTag(content);
    }
    private LinearLayout content(){ return (LinearLayout)root.getTag(); }
    private void header(LinearLayout c,String title){
        LinearLayout h=new LinearLayout(this); h.setGravity(Gravity.CENTER_VERTICAL); h.setPadding(0,0,0,dp(10));
        TextView fr=tv("FR",14,GREEN,true); TextView logo=tv("🌴 DATRIDHA",22,GREEN,true); TextView ar=tv("العربية",14,GREEN,true);
        h.addView(fr,new LinearLayout.LayoutParams(dp(50),dp(50))); h.addView(logo,new LinearLayout.LayoutParams(0,dp(50),1)); h.addView(ar,new LinearLayout.LayoutParams(dp(70),dp(50)));
        fr.setOnClickListener(v->{lang="FR"; showHome();}); ar.setOnClickListener(v->{lang="AR"; showHome();}); c.addView(h);
        TextView line=tv(title,25,BROWN,true); line.setGravity(Gravity.CENTER); c.addView(line,new LinearLayout.LayoutParams(-1,dp(48)));
    }
    private void card(LinearLayout c, View v){ v.setBackground(round(WHITE,18)); v.setPadding(dp(10),dp(8),dp(10),dp(8)); LinearLayout.LayoutParams p=new LinearLayout.LayoutParams(-1,-2); p.setMargins(0,dp(6),0,dp(6)); c.addView(v,p); }
    private void addText(LinearLayout c,String s,float size,int color,boolean bold){ c.addView(tv(s,size,color,bold),new LinearLayout.LayoutParams(-1,-2)); }
    private void tile(LinearLayout grid,String icon,String ar,String fr,int color,View.OnClickListener l){
        Button b=btn(icon+"\n"+(lang.equals("FR")?fr:ar)); b.setTextSize(16); b.setGravity(Gravity.CENTER); b.setBackground(round(color,20)); b.setOnClickListener(l);
        LinearLayout.LayoutParams p=new LinearLayout.LayoutParams(0,dp(100),1); p.setMargins(dp(5),dp(5),dp(5),dp(5)); grid.addView(b,p);
    }
    private void bottom(){
        LinearLayout nav=new LinearLayout(this); nav.setGravity(Gravity.CENTER); nav.setPadding(dp(4),dp(5),dp(4),dp(5)); nav.setBackground(round(GREEN,0));
        String[] a={"⌂\nالرئيسية","🛒\nالعروض","📦\nالطلبات","☎\nتواصل"}; View.OnClickListener[] ls={v->showHome(),v->showBuy(),v->showOrders(),v->showContact()};
        for(int i=0;i<4;i++){ Button b=btn(a[i]); b.setTextSize(12); b.setBackgroundColor(Color.TRANSPARENT); b.setGravity(Gravity.CENTER); b.setOnClickListener(ls[i]); nav.addView(b,new LinearLayout.LayoutParams(0,dp(58),1)); }
        root.addView(nav,new LinearLayout.LayoutParams(-1,dp(68)));
    }

    private void showHome(){
        base("دقلة النور من بشني"); LinearLayout c=content();
        TextView brand=tv("🌴",42,GREEN,true); brand.setGravity(Gravity.CENTER); c.addView(brand,new LinearLayout.LayoutParams(-1,dp(55)));
        TextView d=tv("DATRIDHA",34,GREEN,true); d.setGravity(Gravity.CENTER); d.setLetterSpacing(.08f); c.addView(d,new LinearLayout.LayoutParams(-1,dp(60)));
        addText(c,lang.equals("FR")?"Deglet Nour de Bechni":"دقلة النور من بشني",17,BROWN,true);
        TextView slogan=tv(lang.equals("FR")?"Commerce direct • Qualité • Confiance":"تجارة مباشرة • جودة • ثقة",15,GRAY,false); slogan.setGravity(Gravity.CENTER); c.addView(slogan);
        LinearLayout grid=new LinearLayout(this); grid.setOrientation(LinearLayout.VERTICAL);
        LinearLayout r1=new LinearLayout(this); LinearLayout r2=new LinearLayout(this);
        tile(r1,"🛒","شراء دقلة النور","Acheter","#176B3A".equals("")?GREEN:GREEN,v->showBuy());
        tile(r1,"📤","بيع دقلة النور","Vendre",BROWN,v->showSell());
        tile(r2,"📦","الطلبات","Commandes",GOLD,v->showOrders());
        tile(r2,"☎","تواصل معنا","Contact",LIGHT_GREEN,v->showContact());
        grid.addView(r1); grid.addView(r2); c.addView(grid);
        addText(c,lang.equals("FR")?"Nos produits":"منتجاتنا",21,BROWN,true);
        product(c,"🌴 Deglet Nour - Bechni","كمية متاحة: 5 طن","السعر: حسب الكمية والجودة");
        addText(c,lang.equals("FR")?"Nos engagements":"التزاماتنا",19,BROWN,true);
        LinearLayout badges=new LinearLayout(this); String[] badgesTxt={"✓ موثوق","★ جودة","↔ تجارة مباشرة","🌴 دعم المنتجين"}; for(String s:badgesTxt){ TextView x=tv(s,12,GREEN,true); x.setGravity(Gravity.CENTER); x.setBackground(round(WHITE,14)); LinearLayout.LayoutParams p=new LinearLayout.LayoutParams(0,dp(55),1); p.setMargins(dp(3),0,dp(3),0); badges.addView(x,p);} c.addView(badges);
        bottom();
    }
    private void product(LinearLayout c,String name,String qty,String price){
        LinearLayout box=new LinearLayout(this); box.setOrientation(LinearLayout.VERTICAL); box.setPadding(dp(10),dp(8),dp(10),dp(8));
        TextView image=tv("🌴  🟤🟤🟤",30,BROWN,false); image.setGravity(Gravity.CENTER); box.addView(image,new LinearLayout.LayoutParams(-1,dp(75)));
        addText(box,name,18,GREEN,true); addText(box,qty,14,GRAY,false); addText(box,price,14,BROWN,true);
        Button details=btn(lang.equals("FR")?"Voir détails":"عرض التفاصيل"); details.setOnClickListener(v->showDetails()); box.addView(details,new LinearLayout.LayoutParams(-1,dp(48))); card(c,box);
    }

    private void showBuy(){
        base(lang.equals("FR")?"Acheter la Deglet Nour":"شراء دقلة النور"); LinearLayout c=content();
        EditText search=new EditText(this); search.setHint(lang.equals("FR")?"Rechercher...":"بحث..."); search.setSingleLine(); search.setBackground(round(WHITE,16)); c.addView(search,new LinearLayout.LayoutParams(-1,dp(55)));
        LinearLayout filters=new LinearLayout(this); String[] fs=lang.equals("FR")?new String[]{"Tous","Quantité","Prix","Région"}:new String[]{"الكل","الكمية","السعر","المنطقة"}; for(String f:fs){Button b=btn(f); b.setTextSize(12); b.setBackground(round(LIGHT_GREEN,15)); filters.addView(b,new LinearLayout.LayoutParams(0,dp(45),1));} c.addView(filters);
        offer(c,"دقلة النور - بشني","5 طن","حسب الكمية","قبلي","ممتازة"); offer(c,"دقلة النور - بشني","1 طن","حسب الاتفاق","الفوار","درجة أولى"); bottom();
    }
    private void offer(LinearLayout c,String name,String qty,String price,String region,String quality){
        LinearLayout b=new LinearLayout(this); b.setOrientation(LinearLayout.VERTICAL); addText(b,"🌴  "+name,19,GREEN,true); addText(b,"الكمية: "+qty+"   •   السعر: "+price,15,BROWN,true); addText(b,"المنطقة: "+region+"   •   الجودة: "+quality,14,GRAY,false);
        LinearLayout row=new LinearLayout(this); Button fav=btn("♡"); Button det=btn(lang.equals("FR")?"Détails":"التفاصيل"); fav.setOnClickListener(v->fav.setText("♥")); det.setOnClickListener(v->showDetails()); row.addView(fav,new LinearLayout.LayoutParams(dp(60),dp(48))); row.addView(det,new LinearLayout.LayoutParams(0,dp(48),1)); b.addView(row); card(c,b);
    }

    private void showSell(){
        base(lang.equals("FR")?"Publier une offre":"بيع دقلة النور"); LinearLayout c=content();
        addText(c,lang.equals("FR")?"Informations du vendeur":"معلومات البائع",19,BROWN,true);
        EditText name=input("اسم البائع / Nom du vendeur"); EditText qty=input("الكمية / Quantité"); EditText price=input("السعر / Prix"); EditText phone=input("الهاتف / Téléphone"); EditText desc=input("الوصف والجودة والشروط / Description");
        c.addView(name);c.addView(qty);c.addView(price);c.addView(phone);c.addView(desc);
        Button photo=btn("📷  "+(lang.equals("FR")?"Ajouter des photos":"إضافة صور")); photo.setOnClickListener(v->{Intent i=new Intent(Intent.ACTION_OPEN_DOCUMENT); i.setType("image/*"); i.putExtra(Intent.EXTRA_ALLOW_MULTIPLE,true); i.addCategory(Intent.CATEGORY_OPENABLE); startActivityForResult(i,20);}); c.addView(photo,new LinearLayout.LayoutParams(-1,dp(52)));
        Button publish=btn(lang.equals("FR")?"Publier l'offre":"نشر العرض"); publish.setOnClickListener(v->{ Toast.makeText(this,lang.equals("FR")?"Offre publiée avec succès":"تم نشر العرض بنجاح",Toast.LENGTH_LONG).show(); showOrders();}); c.addView(publish,new LinearLayout.LayoutParams(-1,dp(58)));
        bottom();
    }
    private EditText input(String hint){ EditText e=new EditText(this); e.setHint(hint); e.setTextSize(15); e.setSingleLine(false); e.setBackground(round(WHITE,14)); e.setPadding(dp(12),dp(5),dp(12),dp(5)); LinearLayout.LayoutParams p=new LinearLayout.LayoutParams(-1,dp(58)); p.setMargins(0,dp(5),0,dp(5)); e.setLayoutParams(p); return e; }

    private void showOrders(){
        base(lang.equals("FR")?"Mes commandes":"الطلبات"); LinearLayout c=content();
        LinearLayout filters=new LinearLayout(this); String[] fs=lang.equals("FR")?new String[]{"Toutes","En cours","Reçues"}:new String[]{"الكل","قيد التنفيذ","مستلمة"}; for(String f:fs){Button b=btn(f); b.setTextSize(12); b.setBackground(round(LIGHT_GREEN,14)); filters.addView(b,new LinearLayout.LayoutParams(0,dp(45),1));} c.addView(filters);
        order(c,"شراء دقلة النور","2 طن","06/10/2026","En cours","قيد التنفيذ"); order(c,"شراء دقلة النور","1 طن","05/10/2026","Acceptée","مقبولة"); order(c,"شراء دقلة النور","500 كغ","01/10/2026","En attente","في الانتظار"); bottom();
    }
    private void order(LinearLayout c,String type,String qty,String date,String fr,String ar){ LinearLayout b=new LinearLayout(this); b.setOrientation(LinearLayout.VERTICAL); addText(b,"📦  "+type,18,BROWN,true); addText(b,"الكمية: "+qty+"   •   التاريخ: "+date,14,GRAY,false); addText(b,"الحالة: "+(lang.equals("FR")?fr:ar),15,GREEN,true); card(c,b); }

    private void showContact(){
        base(lang.equals("FR")?"Contactez-nous":"تواصل معنا"); LinearLayout c=content();
        addText(c,"DATRIDHA",27,GREEN,true); addText(c,lang.equals("FR")?"Commerce direct de Deglet Nour de Bechni":"تجارة مباشرة لدقلة النور من بشني",16,BROWN,false);
        addText(c,"☎  +216 51 022 448",18,GREEN,true); addText(c,"✉  ridhatouil1992@gmail.com",16,BROWN,false);
        Button call=btn(lang.equals("FR")?"Appeler":"اتصال هاتفي"); call.setOnClickListener(v->{Intent i=new Intent(Intent.ACTION_DIAL, Uri.parse("tel:+21651022448")); startActivity(i);}); c.addView(call,new LinearLayout.LayoutParams(-1,dp(55)));
        Button mail=btn(lang.equals("FR")?"Envoyer un e-mail":"إرسال بريد إلكتروني"); mail.setOnClickListener(v->{Intent i=new Intent(Intent.ACTION_SENDTO,Uri.parse("mailto:ridhatouil1992@gmail.com")); startActivity(i);}); c.addView(mail,new LinearLayout.LayoutParams(-1,dp(55)));
        Button settings=btn(lang.equals("FR")?"Paramètres":"الإعدادات"); settings.setOnClickListener(v->showSettings()); c.addView(settings,new LinearLayout.LayoutParams(-1,dp(55)));
        bottom();
    }

    private void showSettings(){
        base(lang.equals("FR")?"Paramètres":"الإعدادات"); LinearLayout c=content();
        setting(c,lang.equals("FR")?"Langue":"اللغة",lang.equals("FR")?"Français / العربية":"العربية / Français",v->{lang=lang.equals("FR")?"AR":"FR";showSettings();});
        setting(c,lang.equals("FR")?"Notifications":"الإشعارات",lang.equals("FR")?"Activées":"مفعلة",v->Toast.makeText(this,"✓",Toast.LENGTH_SHORT).show());
        setting(c,lang.equals("FR")?"À propos":"حول التطبيق",lang.equals("FR")?"DATRIDHA":"داتريضة",v->showAbout());
        setting(c,lang.equals("FR")?"Confidentialité":"الخصوصية",lang.equals("FR")?"Vos données sont protégées":"بياناتك محمية",v->Toast.makeText(this,lang.equals("FR")?"Confidentialité DATRIDHA":"خصوصية DATRIDHA",Toast.LENGTH_LONG).show());
        setting(c,lang.equals("FR")?"Aide":"المساعدة",lang.equals("FR")?"Contactez-nous":"تواصل معنا",v->showContact()); bottom();
    }
    private void setting(LinearLayout c,String a,String b,View.OnClickListener l){ LinearLayout x=new LinearLayout(this); x.setOrientation(LinearLayout.VERTICAL); x.setPadding(dp(12),dp(7),dp(12),dp(7)); addText(x,a,17,BROWN,true); addText(x,b,13,GRAY,false); x.setOnClickListener(l); card(c,x); }
    private void showAbout(){ base(lang.equals("FR")?"À propos de DATRIDHA":"حول DATRIDHA"); LinearLayout c=content(); TextView x=tv("🌴\nDATRIDHA\n\n"+(lang.equals("FR")?"Plateforme de commerce direct de Deglet Nour de Bechni.\nAchetez, vendez et suivez vos commandes simplement.":"منصة للتجارة المباشرة لدقلة النور من بشني.\nاشترِ وبِع وتابع طلباتك بسهولة."),19,BROWN,true); x.setGravity(Gravity.CENTER); c.addView(x,new LinearLayout.LayoutParams(-1,dp(250))); Button b=btn(lang.equals("FR")?"Contact":"تواصل معنا"); b.setOnClickListener(v->showContact()); c.addView(b,new LinearLayout.LayoutParams(-1,dp(55))); bottom(); }
    private void showDetails(){ base(lang.equals("FR")?"Détails de l'offre":"تفاصيل العرض"); LinearLayout c=content(); TextView img=tv("🌴\n🟤 🟤 🟤\n🟤 🟤 🟤",34,BROWN,true); img.setGravity(Gravity.CENTER); c.addView(img,new LinearLayout.LayoutParams(-1,dp(210))); addText(c,"Deglet Nour - Bechni",24,GREEN,true); addText(c,"السعر: حسب الكمية والجودة",17,BROWN,true); addText(c,"الكمية: حتى 5 طن",16,GRAY,false); addText(c,"الأصل: بشني - قبلي",16,GRAY,false); addText(c,"الجودة: ممتازة",16,GRAY,false); addText(c,"التغليف: حسب الاتفاق",16,GRAY,false); Button contact=btn(lang.equals("FR")?"Contacter le vendeur":"التواصل مع صاحب العرض"); contact.setOnClickListener(v->showContact()); c.addView(contact,new LinearLayout.LayoutParams(-1,dp(58))); bottom(); }

    @Override public void onBackPressed(){ showHome(); }
}
