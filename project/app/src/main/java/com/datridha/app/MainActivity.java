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
import android.view.ViewGroup;
import android.widget.*;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    private static final int GREEN = Color.rgb(23,107,58);
    private static final int LIGHT_GREEN = Color.rgb(46,139,87);
    private static final int GOLD = Color.rgb(212,175,55);
    private static final int BROWN = Color.rgb(91,58,30);
    private static final int BEIGE = Color.rgb(247,233,215);
    private static final int WHITE = Color.WHITE;
    private static final int TEXT = Color.rgb(55,45,38);
    private LinearLayout root;
    private String language = "ar";
    private String currentPage = "home";
    private final List<String[]> offers = new ArrayList<>();

    @Override public void onCreate(Bundle b) {
        super.onCreate(b);
        seedOffers();
        showHome();
    }

    private void seedOffers() {
        offers.clear();
        offers.add(new String[]{"دقلة النور – بشني", "5000 كغ", "4.800 د.ت/كغ", "بشني – الفوار", "ممتازة", "تغليف غذائي"});
        offers.add(new String[]{"دقلة النور – بشني", "2000 كغ", "5.100 د.ت/كغ", "بشني – قبلي", "ممتازة", "صناديق 10 كغ"});
        offers.add(new String[]{"دقلة النور – إنتاج مباشر", "10000 كغ", "4.600 د.ت/كغ", "الفوار – قبلي", "درجة أولى", "حسب الاتفاق"});
    }

    private int dp(int n) { return (int)(n * getResources().getDisplayMetrics().density + .5f); }
    private TextView text(String s, int size, int color, boolean bold) {
        TextView v = new TextView(this); v.setText(s); v.setTextSize(size); v.setTextColor(color);
        v.setTypeface(Typeface.create("sans", bold ? Typeface.BOLD : Typeface.NORMAL));
        v.setGravity(Gravity.CENTER_VERTICAL); v.setPadding(dp(8),dp(5),dp(8),dp(5)); return v;
    }
    private GradientDrawable bg(int color, int radius) { GradientDrawable g=new GradientDrawable(); g.setColor(color); g.setCornerRadius(dp(radius)); return g; }
    private Button btn(String s, int color) { Button b=new Button(this); b.setText(s); b.setTextSize(15); b.setTextColor(WHITE); b.setAllCaps(false); b.setTypeface(Typeface.DEFAULT,Typeface.BOLD); b.setBackground(bg(color,14)); b.setPadding(dp(8),dp(5),dp(8),dp(5)); return b; }
    private EditText input(String hint) { EditText e=new EditText(this); e.setHint(hint); e.setTextSize(15); e.setSingleLine(false); e.setPadding(dp(14),dp(10),dp(14),dp(10)); e.setBackground(bg(WHITE,12)); return e; }
    private LinearLayout box() { LinearLayout l=new LinearLayout(this); l.setOrientation(LinearLayout.VERTICAL); l.setPadding(dp(12),dp(10),dp(12),dp(10)); l.setBackground(bg(WHITE,16)); return l; }
    private void add(View v, ViewGroup p, int w, int h) { p.addView(v,new LinearLayout.LayoutParams(w,h)); }
    private void addWeight(View v, ViewGroup p) { p.addView(v,new LinearLayout.LayoutParams(0,LinearLayout.LayoutParams.WRAP_CONTENT,1)); }

    private void prepare(String title) {
        root=new LinearLayout(this); root.setOrientation(LinearLayout.VERTICAL); root.setBackgroundColor(BEIGE);
        setContentView(root);
        LinearLayout top=new LinearLayout(this); top.setGravity(Gravity.CENTER_VERTICAL); top.setPadding(dp(10),dp(8),dp(10),dp(8)); top.setBackgroundColor(GREEN);
        TextView back=text("‹",32,WHITE,true); back.setGravity(Gravity.CENTER); back.setOnClickListener(v -> goHome()); add(back,top,dp(42),dp(48));
        TextView t=text(title,20,WHITE,true); t.setGravity(Gravity.CENTER); addWeight(t,top);
        TextView lang=text(language.equals("ar")?"FR":"عربي",15,WHITE,true); lang.setGravity(Gravity.CENTER); lang.setOnClickListener(v->{language=language.equals("ar")?"fr":"ar"; refresh();}); add(lang,top,dp(55),dp(48));
        add(top,root,LinearLayout.LayoutParams.MATCH_PARENT,dp(64));
        ScrollView sv=new ScrollView(this); LinearLayout body=new LinearLayout(this); body.setOrientation(LinearLayout.VERTICAL); body.setPadding(dp(14),dp(14),dp(14),dp(90)); sv.addView(body); add(sv,root,LinearLayout.LayoutParams.MATCH_PARENT,0); ((LinearLayout.LayoutParams)sv.getLayoutParams()).weight=1;
        bottomNav();
    }

    private LinearLayout body() { return (LinearLayout)((ScrollView)root.getChildAt(1)).getChildAt(0); }
    private void bottomNav() {
        LinearLayout nav=new LinearLayout(this); nav.setPadding(dp(5),dp(4),dp(5),dp(4)); nav.setBackgroundColor(WHITE);
        String[] labels={language.equals("ar")?"الرئيسية":"Accueil",language.equals("ar")?"العروض":"Offres",language.equals("ar")?"الطلبات":"Commandes",language.equals("ar")?"تواصل":"Contact"};
        String[] pages={"home","buy","orders","contact"};
        for(int i=0;i<4;i++){ Button b=btn(labels[i],i==0?GREEN:LIGHT_GREEN); final String p=pages[i]; b.setOnClickListener(v->{if(p.equals("home"))showHome();else if(p.equals("buy"))showBuy();else if(p.equals("orders"))showOrders();else showContact();}); addWeight(b,nav); }
        add(nav,root,LinearLayout.LayoutParams.MATCH_PARENT,dp(62));
    }

    private void goHome(){ showHome(); }
    private void refresh(){ if(currentPage.equals("home"))showHome(); else if(currentPage.equals("buy"))showBuy(); else if(currentPage.equals("sell"))showSell(); else if(currentPage.equals("orders"))showOrders(); else if(currentPage.equals("contact"))showContact(); else if(currentPage.equals("settings"))showSettings(); else if(currentPage.equals("about"))showAbout(); }

    private void headerHome(LinearLayout b){
        LinearLayout h=new LinearLayout(this); h.setGravity(Gravity.CENTER_VERTICAL); h.setPadding(dp(4),dp(5),dp(4),dp(5));
        TextView ar=text("العربية",14,GREEN,true); ar.setOnClickListener(v->{language="ar";showHome();}); add(ar,h,dp(62),dp(45));
        TextView brand=text("🌴 DATRIDHA",24,GREEN,true); brand.setGravity(Gravity.CENTER); addWeight(brand,h);
        TextView fr=text("Français",14,GREEN,true); fr.setOnClickListener(v->{language="fr";showHome();}); add(fr,h,dp(65),dp(45)); add(h,b,LinearLayout.LayoutParams.MATCH_PARENT,dp(55));
    }

    private void showHome(){
        currentPage="home"; root=new LinearLayout(this); root.setOrientation(LinearLayout.VERTICAL); root.setBackgroundColor(BEIGE); setContentView(root);
        ScrollView sv=new ScrollView(this); LinearLayout b=new LinearLayout(this); b.setOrientation(LinearLayout.VERTICAL); b.setPadding(dp(14),dp(12),dp(14),dp(18)); sv.addView(b); add(sv,root,LinearLayout.LayoutParams.MATCH_PARENT,0); ((LinearLayout.LayoutParams)sv.getLayoutParams()).weight=1;
        headerHome(b);
        TextView title=text("🌴 DATRIDHA",30,GREEN,true); title.setGravity(Gravity.CENTER); add(title,b,LinearLayout.LayoutParams.MATCH_PARENT,dp(55));
        TextView sub=text(language.equals("ar")?"دَقْلَةُ النُّور • بَشْنِي":"Deglet Nour • Bechni",18,BROWN,true); sub.setGravity(Gravity.CENTER); add(sub,b,LinearLayout.LayoutParams.MATCH_PARENT,dp(42));
        TextView slogan=text(language.equals("ar")?"تجارة مباشرة • جودة • ثقة":"Commerce direct • Qualité • Confiance",15,TEXT,false); slogan.setGravity(Gravity.CENTER); add(slogan,b,LinearLayout.LayoutParams.MATCH_PARENT,dp(36));
        LinearLayout grid=new LinearLayout(this); grid.setOrientation(LinearLayout.VERTICAL);
        LinearLayout r1=new LinearLayout(this); LinearLayout r2=new LinearLayout(this);
        addTile(r1,language.equals("ar")?"🛒\nشراء دقلة النور":"🛒\nAcheter",GREEN,()->showBuy()); addTile(r1,language.equals("ar")?"🌴\nبيع دقلة النور":"🌴\nVendre",GOLD,()->showSell());
        addTile(r2,language.equals("ar")?"📦\nالطلبات":"📦\nCommandes",LIGHT_GREEN,()->showOrders()); addTile(r2,language.equals("ar")?"☎\nتواصل معنا":"☎\nContact",BROWN,()->showContact());
        add(r1,grid,LinearLayout.LayoutParams.MATCH_PARENT,dp(105)); add(r2,grid,LinearLayout.LayoutParams.MATCH_PARENT,dp(105)); add(grid,b,LinearLayout.LayoutParams.MATCH_PARENT,dp(210));
        TextView offersTitle=text(language.equals("ar")?"أحدث عروض دقلة النور":"Dernières offres",21,GREEN,true); add(offersTitle,b,LinearLayout.LayoutParams.MATCH_PARENT,dp(45));
        for(int i=0;i<Math.min(2,offers.size());i++) addOfferCard(b,i);
        LinearLayout badges=new LinearLayout(this); String[] bs=language.equals("ar")?new String[]{"✓ موثوق","✓ جودة","✓ تجارة مباشرة","✓ دعم المنتجين"}:new String[]{"✓ Fiable","✓ Qualité","✓ Commerce direct","✓ Soutien aux producteurs"}; for(String x:bs){TextView q=text(x,12,GREEN,true); q.setGravity(Gravity.CENTER); addWeight(q,badges);} add(badges,b,LinearLayout.LayoutParams.MATCH_PARENT,dp(55));
        bottomNav();
    }

    private void addTile(LinearLayout row,String label,int color,final Runnable action){ Button b=btn(label,color); b.setTextSize(16); b.setGravity(Gravity.CENTER); b.setOnClickListener(v->action.run()); LinearLayout.LayoutParams p=new LinearLayout.LayoutParams(0,dp(98),1); p.setMargins(dp(5),dp(5),dp(5),dp(5)); row.addView(b,p); }

    private void addOfferCard(LinearLayout parent,int idx){
        String[] o=offers.get(idx); LinearLayout c=box(); TextView n=text("🌴 "+o[0],18,GREEN,true); add(n,c,LinearLayout.LayoutParams.MATCH_PARENT,dp(38)); TextView info=text("الكمية: "+o[1]+"   |   "+o[2]+"\nالمنطقة: "+o[3]+"   |   الجودة: "+o[4],14,TEXT,false); add(info,c,LinearLayout.LayoutParams.MATCH_PARENT,dp(58)); Button d=btn(language.equals("ar")?"تفاصيل العرض":"Voir l'offre",GOLD); d.setOnClickListener(v->showDetails(idx)); add(d,c,LinearLayout.LayoutParams.MATCH_PARENT,dp(48)); LinearLayout.LayoutParams cp=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,dp(155)); cp.setMargins(0,0,0,dp(10)); parent.addView(c,cp);
    }

    private void showBuy(){
        currentPage="buy"; prepare(language.equals("ar")?"شراء دقلة النور":"Acheter Deglet Nour"); LinearLayout b=body();
        TextView intro=text(language.equals("ar")?"عروض البيع بالجملة من المنتجين":"Offres de vente en gros des producteurs",16,BROWN,true); intro.setGravity(Gravity.CENTER); add(intro,b,LinearLayout.LayoutParams.MATCH_PARENT,dp(42));
        EditText search=input(language.equals("ar")?"🔎 ابحث عن عرض أو منطقة...":"🔎 Rechercher une offre ou une région..."); add(search,b,LinearLayout.LayoutParams.MATCH_PARENT,dp(55));
        LinearLayout filters=new LinearLayout(this); String[] fs=language.equals("ar")?new String[]{"الكل","الكمية","السعر","المنطقة"}:new String[]{"Tous","Quantité","Prix","Région"}; for(String f:fs){Button x=btn(f,WHITE); x.setTextColor(GREEN); x.setTextSize(12); x.setOnClickListener(v->Toast.makeText(this,f,Toast.LENGTH_SHORT).show()); addWeight(x,filters);} add(filters,b,LinearLayout.LayoutParams.MATCH_PARENT,dp(52));
        TextView count=text(language.equals("ar")?"العروض المتاحة":"Offres disponibles",19,GREEN,true); add(count,b,LinearLayout.LayoutParams.MATCH_PARENT,dp(45));
        for(int i=0;i<offers.size();i++) addOfferCard(b,i);
        Button publish=btn(language.equals("ar")?"＋ نشر عرض بيع":"＋ Publier une offre",GREEN); publish.setOnClickListener(v->showSell()); add(publish,b,LinearLayout.LayoutParams.MATCH_PARENT,dp(52));
    }

    private void showSell(){
        currentPage="sell"; prepare(language.equals("ar")?"نشر عرض للبيع":"Publier une offre"); LinearLayout b=body();
        TextView intro=text(language.equals("ar")?"أدخل معلومات دقلة النور المعروضة للبيع بالجملة":"Saisissez les informations de votre offre",16,BROWN,true); add(intro,b,LinearLayout.LayoutParams.MATCH_PARENT,dp(45));
        EditText name=input(language.equals("ar")?"اسم البائع / الشركة":"Nom / Société"); EditText qty=input(language.equals("ar")?"الكمية (كغ أو طن)":"Quantité (kg ou tonnes)"); EditText price=input(language.equals("ar")?"السعر المطلوب (د.ت/كغ)":"Prix demandé (DT/kg)"); EditText phone=input(language.equals("ar")?"رقم الهاتف":"Téléphone"); EditText desc=input(language.equals("ar")?"الوصف والجودة وشروط البيع":"Description, qualité et conditions");
        add(name,b,LinearLayout.LayoutParams.MATCH_PARENT,dp(55)); add(qty,b,LinearLayout.LayoutParams.MATCH_PARENT,dp(55)); add(price,b,LinearLayout.LayoutParams.MATCH_PARENT,dp(55)); add(phone,b,LinearLayout.LayoutParams.MATCH_PARENT,dp(55)); add(desc,b,LinearLayout.LayoutParams.MATCH_PARENT,dp(90));
        Button photo=btn(language.equals("ar")?"📷 إرفاق صور":"📷 Ajouter des photos",BROWN); photo.setOnClickListener(v->{Intent i=new Intent(Intent.ACTION_OPEN_DOCUMENT); i.setType("image/*"); i.putExtra(Intent.EXTRA_ALLOW_MULTIPLE,true); i.addCategory(Intent.CATEGORY_OPENABLE); startActivityForResult(i,10);}); add(photo,b,LinearLayout.LayoutParams.MATCH_PARENT,dp(52));
        Button pub=btn(language.equals("ar")?"نشر العرض":"Publier l'offre",GREEN); pub.setOnClickListener(v->Toast.makeText(this,language.equals("ar")?"تم تجهيز العرض للنشر":"Offre prête à publier",Toast.LENGTH_SHORT).show()); add(pub,b,LinearLayout.LayoutParams.MATCH_PARENT,dp(55));
    }

    private void showOrders(){
        currentPage="orders"; prepare(language.equals("ar")?"الطلبات":"Commandes"); LinearLayout b=body();
        LinearLayout filters=new LinearLayout(this); String[] fs=language.equals("ar")?new String[]{"الكل","قيد التنفيذ","مستلمة"}:new String[]{"Toutes","En cours","Reçues"}; for(String f:fs){Button x=btn(f,WHITE); x.setTextColor(GREEN); addWeight(x,filters);} add(filters,b,LinearLayout.LayoutParams.MATCH_PARENT,dp(52));
        addOrder(b,"شراء 5000 كغ","06/10/2026",language.equals("ar")?"قيد التنفيذ":"En cours",Color.rgb(40,110,190));
        addOrder(b,"شراء 2000 كغ","05/10/2026",language.equals("ar")?"مقبولة":"Acceptée",GREEN);
        addOrder(b,"شراء 1000 كغ","02/10/2026",language.equals("ar")?"في الانتظار":"En attente",GOLD);
    }
    private void addOrder(LinearLayout b,String title,String date,String status,int color){ LinearLayout c=box(); add(text("📦 "+title,17,GREEN,true),c,LinearLayout.LayoutParams.MATCH_PARENT,dp(38)); add(text(date,13,TEXT,false),c,LinearLayout.LayoutParams.MATCH_PARENT,dp(28)); TextView st=text(status,14,color,true); st.setGravity(Gravity.CENTER); st.setBackground(bg(Color.argb(35, color==GOLD?180:0, color==GOLD?140:100, color==GOLD?0:180),12)); add(st,c,LinearLayout.LayoutParams.MATCH_PARENT,dp(40)); LinearLayout.LayoutParams p=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,dp(115)); p.setMargins(0,0,0,dp(10)); b.addView(c,p); }

    private void showContact(){
        currentPage="contact"; prepare(language.equals("ar")?"تواصل معنا":"Contact"); LinearLayout b=body();
        TextView logo=text("🌴 DATRIDHA",28,GREEN,true); logo.setGravity(Gravity.CENTER); add(logo,b,LinearLayout.LayoutParams.MATCH_PARENT,dp(60));
        TextView info=text("دقلة النور من بشني\n\n📞 +216 51 022 448\n✉ ridhatouil1992@gmail.com",17,TEXT,true); info.setGravity(Gravity.CENTER); add(info,b,LinearLayout.LayoutParams.MATCH_PARENT,dp(125));
        Button call=btn(language.equals("ar")?"📞 اتصال":"📞 Appeler",GREEN); call.setOnClickListener(v->startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:+21651022448")))); add(call,b,LinearLayout.LayoutParams.MATCH_PARENT,dp(55));
        Button email=btn(language.equals("ar")?"✉ إرسال بريد إلكتروني":"✉ Envoyer un email",BROWN); email.setOnClickListener(v->{Intent i=new Intent(Intent.ACTION_SENDTO,Uri.parse("mailto:ridhatouil1992@gmail.com")); startActivity(i);}); add(email,b,LinearLayout.LayoutParams.MATCH_PARENT,dp(55));
        Button settings=btn(language.equals("ar")?"⚙ الإعدادات":"⚙ Paramètres",GOLD); settings.setTextColor(TEXT); settings.setOnClickListener(v->showSettings()); add(settings,b,LinearLayout.LayoutParams.MATCH_PARENT,dp(55));
    }

    private void showSettings(){
        currentPage="settings"; prepare(language.equals("ar")?"الإعدادات":"Paramètres"); LinearLayout b=body();
        setting(b,language.equals("ar")?"🌐 اللغة":"🌐 Langue",language.equals("ar")?"العربية / Français":"Français / العربية",()->{language=language.equals("ar")?"fr":"ar";showSettings();});
        setting(b,language.equals("ar")?"🔔 الإشعارات":"🔔 Notifications",language.equals("ar")?"مفعلة":"Activées",()->Toast.makeText(this,"✓",Toast.LENGTH_SHORT).show());
        setting(b,language.equals("ar")?"ℹ حول DATRIDHA":"ℹ À propos", "DATRIDHA",()->showAbout());
        setting(b,language.equals("ar")?"🔒 الخصوصية":"🔒 Confidentialité",language.equals("ar")?"حماية معلومات المستخدم":"Protection des données",()->Toast.makeText(this,"DATRIDHA",Toast.LENGTH_SHORT).show());
        setting(b,language.equals("ar")?"❓ المساعدة":"❓ Aide",language.equals("ar")?"تواصل معنا عند الحاجة":"Contactez-nous",()->showContact());
    }
    private void setting(LinearLayout b,String a,String c,final Runnable r){LinearLayout x=box(); TextView t=text(a,17,GREEN,true); add(t,x,LinearLayout.LayoutParams.MATCH_PARENT,dp(38)); TextView q=text(c,14,TEXT,false); add(q,x,LinearLayout.LayoutParams.MATCH_PARENT,dp(32)); x.setOnClickListener(v->r.run()); LinearLayout.LayoutParams p=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,dp(90)); p.setMargins(0,0,0,dp(10)); b.addView(x,p);}

    private void showAbout(){
        currentPage="about"; prepare(language.equals("ar")?"حول DATRIDHA":"À propos de DATRIDHA"); LinearLayout b=body();
        TextView t=text("🌴 DATRIDHA",30,GREEN,true); t.setGravity(Gravity.CENTER); add(t,b,LinearLayout.LayoutParams.MATCH_PARENT,dp(60));
        TextView q=text(language.equals("ar")?"منصة رقمية لتسهيل التجارة المباشرة بالجملة لدقلة النور من بشني، وربط المنتجين بالمشترين بطريقة واضحة وموثوقة.\n\nالجودة • الثقة • التجارة المباشرة • دعم المنتجين":"Plateforme numérique dédiée au commerce direct de Deglet Nour en gros depuis Bechni, reliant producteurs et acheteurs de manière simple et fiable.\n\nQualité • Confiance • Commerce direct • Soutien aux producteurs",16,TEXT,false); q.setGravity(Gravity.CENTER); add(q,b,LinearLayout.LayoutParams.MATCH_PARENT,dp(220));
        TextView v=text("DATRIDHA • Bechni – Kebili",14,BROWN,true); v.setGravity(Gravity.CENTER); add(v,b,LinearLayout.LayoutParams.MATCH_PARENT,dp(45));
    }

    private void showDetails(int idx){
        currentPage="details"; prepare(language.equals("ar")?"تفاصيل العرض":"Détails de l'offre"); LinearLayout b=body(); String[] o=offers.get(idx);
        TextView image=text("🌴\n🌴  دقلة النور  🌴",26,GREEN,true); image.setGravity(Gravity.CENTER); image.setBackground(bg(Color.rgb(235,220,180),18)); add(image,b,LinearLayout.LayoutParams.MATCH_PARENT,dp(155));
        TextView n=text(o[0],23,GREEN,true); n.setGravity(Gravity.CENTER); add(n,b,LinearLayout.LayoutParams.MATCH_PARENT,dp(50));
        add(text("💰 "+o[2]+"\n📦 "+o[1]+"\n📍 "+o[3]+"\n⭐ "+o[4]+"\n📦 "+o[5],17,TEXT,false),b,LinearLayout.LayoutParams.MATCH_PARENT,dp(145));
        LinearLayout row=new LinearLayout(this); Button fav=btn("♡ "+(language.equals("ar")?"مفضلة":"Favori"),GOLD); fav.setTextColor(TEXT); Button contact=btn(language.equals("ar")?"☎ تواصل مع صاحب العرض":"☎ Contacter le vendeur",GREEN); fav.setOnClickListener(v->Toast.makeText(this,"♥",Toast.LENGTH_SHORT).show()); contact.setOnClickListener(v->showContact()); addWeight(fav,row); addWeight(contact,row); add(row,b,LinearLayout.LayoutParams.MATCH_PARENT,dp(58));
    }

    @Override public void onBackPressed(){ if(!currentPage.equals("home")) showHome(); else super.onBackPressed(); }
}
