package com.datridha.app;

import android.app.Activity;
import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.graphics.*;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.*;
import android.widget.*;

import java.util.*;

public class MainActivity extends Activity {

    static final int GREEN = Color.rgb(23,107,58);
    static final int LIGHT_GREEN = Color.rgb(46,139,87);
    static final int GOLD = Color.rgb(212,175,55);
    static final int BROWN = Color.rgb(91,58,30);
    static final int IVORY = Color.rgb(247,233,215);
    static final int CREAM = Color.rgb(249,244,236);
    static final int WHITE = Color.WHITE;
    static final int GRAY = Color.rgb(102,102,102);

    LinearLayout root;
    String language = "ar";

    String tr(String ar, String fr, String en) {
        if ("fr".equals(language)) return fr;
        if ("en".equals(language)) return en;
        return ar;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showSplash();
    }

    void showSplash() {
        root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setGravity(Gravity.CENTER_HORIZONTAL);
        root.setPadding(25,35,25,25);

        GradientDrawable bg = new GradientDrawable(
                GradientDrawable.Orientation.TL_BR,
                new int[]{
                        Color.rgb(10,60,32),
                        GREEN,
                        LIGHT_GREEN
                }
        );
        root.setBackground(bg);

        Space top = new Space(this);
        root.addView(top,new LinearLayout.LayoutParams(1,25));

        ArtView art = new ArtView(this);
        art.type = 0;
        root.addView(art,new LinearLayout.LayoutParams(-1,230));

        TextView logo = text("DATRIDHA",34,WHITE,true);
        logo.setGravity(Gravity.CENTER);
        root.addView(logo);

        TextView ar = text("دقلة النور من بشني",21,Color.rgb(255,239,185),true);
        ar.setGravity(Gravity.CENTER);
        root.addView(ar);

        TextView fr = text("Deglet Nour de Bechni",17,WHITE,false);
        fr.setGravity(Gravity.CENTER);
        root.addView(fr);

        TextView slogan = text(
                "Achetez • Vendez • Échangez",
                15,WHITE,false
        );
        slogan.setGravity(Gravity.CENTER);
        root.addView(slogan);

        Space sp = new Space(this);
        root.addView(sp,new LinearLayout.LayoutParams(1,20));

        Button start = button(
                tr("ابدأ الآن","Commencer","Start now"),
                GREEN
        );
        root.addView(start,new LinearLayout.LayoutParams(-1,56));
        start.setOnClickListener(v -> showHome());

        LinearLayout langs = new LinearLayout(this);
        langs.setGravity(Gravity.CENTER);

        String[] names = {"العربية","Français","English"};

        for(String l:names) {
            TextView b = text(l,13,WHITE,false);
            b.setGravity(Gravity.CENTER);
            langs.addView(b,new LinearLayout.LayoutParams(0,45,1));

            if(l.equals("العربية"))
                b.setOnClickListener(v->{language="ar";showSplash();});
            else if(l.equals("Français"))
                b.setOnClickListener(v->{language="fr";showSplash();});
            else
                b.setOnClickListener(v->{language="en";showSplash();});
        }

        root.addView(langs);
        setContentView(root);
    }

    void showHome() {
        baseScreen();

        addHeader(tr("الرئيسية","Accueil","Home"),true);

        ArtView banner = new ArtView(this);
        banner.type = 1;
        root.addView(banner,new LinearLayout.LayoutParams(-1,210));

        TextView title = text(
                tr("دقلة النور من بشني",
                        "Deglet Nour de Bechni",
                        "Deglet Nour from Bechni"),
                23,BROWN,true
        );
        title.setGravity(Gravity.CENTER);
        title.setPadding(0,12,0,6);
        root.addView(title);

        TextView sub = text(
                tr("اشتر • بع • تعامل مباشرة",
                        "Achetez • Vendez • Échangez directement",
                        "Buy • Sell • Trade directly"),
                14,GRAY,false
        );
        sub.setGravity(Gravity.CENTER);
        root.addView(sub);

        LinearLayout grid = new LinearLayout(this);
        grid.setOrientation(LinearLayout.VERTICAL);
        grid.setPadding(0,12,0,5);

        addHomeRow(grid,
                tr("شراء","Acheter","Buy"),
                tr("بيع","Vendre","Sell"),2,3);

        addHomeRow(grid,
                tr("الطلبات","Commandes","Orders"),
                tr("اتصل بنا","Contact","Contact"),4,5);

        root.addView(grid);

        TextView products = text(
                tr("عروض دقلة النور",
                        "Offres Deglet Nour",
                        "Deglet Nour offers"),
                20,GREEN,true
        );
        products.setPadding(5,15,5,8);
        root.addView(products);

        addProductCard(
                tr("دقلة نور ممتازة","Deglet Nour Premium","Premium Deglet Nour"),
                "5","DT/kg","Bechni"
        );

        addProductCard(
                tr("دقلة نور جودة أولى","Deglet Nour Qualité 1","Deglet Nour Quality 1"),
                "6","DT/kg","Kebili"
        );

        addBottomNav(0);
    }

    void addHomeRow(
            LinearLayout parent,
            String one,String two,
            int typeOne,int typeTwo) {

        LinearLayout row = new LinearLayout(this);
        row.setOrientation(LinearLayout.HORIZONTAL);
        row.setPadding(0,4,0,4);

        Button a = menuButton(one);
        Button b = menuButton(two);

        row.addView(a,new LinearLayout.LayoutParams(0,88,1));
        row.addView(b,new LinearLayout.LayoutParams(0,88,1));

        a.setOnClickListener(v->openType(typeOne));
        b.setOnClickListener(v->openType(typeTwo));

        parent.addView(row);
    }

    void openType(int type) {
        if(type==2) showBuy();
        else if(type==3) showSell();
        else if(type==4) showOrders();
        else if(type==5) showContact();
    }

    Button menuButton(String title) {
        Button b = button(title,GREEN);
        b.setTextSize(17);
        b.setAllCaps(false);
        return b;
    }

    void showBuy() {
        baseScreen();
        addHeader(tr("شراء","Acheter","Buy"),true);

        EditText search = new EditText(this);
        search.setHint(
                tr("ابحث عن دقلة النور...",
                        "Rechercher Deglet Nour...",
                        "Search Deglet Nour...")
        );
        search.setSingleLine(true);
        search.setPadding(18,0,18,0);

        root.addView(search,new LinearLayout.LayoutParams(-1,55));

        LinearLayout filters = new LinearLayout(this);

        String[] fs={
                tr("الكل","Tout","All"),
                tr("الكمية","Quantité","Quantity"),
                tr("السعر","Prix","Price"),
                tr("المنطقة","Région","Region")
        };

        for(String f:fs) {
            TextView t=text(f,12,GREEN,true);
            t.setGravity(Gravity.CENTER);
            filters.addView(t,new LinearLayout.LayoutParams(0,45,1));
        }

        root.addView(filters);

        addOffer(
                tr("دقلة نور ممتازة","Deglet Nour Premium","Premium Deglet Nour"),
                "5 DT/kg","5 tonnes","Bechni"
        );

        addOffer(
                tr("دقلة نور جودة أولى","Deglet Nour Qualité 1","Deglet Nour Quality 1"),
                "6 DT/kg","2 tonnes","El Fawar"
        );

        addOffer(
                tr("دقلة نور فاخرة","Deglet Nour Luxe","Luxury Deglet Nour"),
                "7 DT/kg","1 tonne","Kebili"
        );

        addBottomNav(1);
    }

    void addOffer(
            String name,String price,
            String quantity,String region) {

        LinearLayout c=card();

        ArtView art=new ArtView(this);
        art.type=2;
        c.addView(art,new LinearLayout.LayoutParams(-1,125));

        TextView n=text(name,19,GREEN,true);
        n.setPadding(5,10,5,4);
        c.addView(n);

        c.addView(text(
                price+"   •   "+quantity+"   •   "+region,
                14,BROWN,false
        ));

        Button details=button(
                tr("التفاصيل","Détails","Details"),GREEN
        );
        c.addView(details,new LinearLayout.LayoutParams(-1,50));
        details.setOnClickListener(v->showOfferDetails(name));

        root.addView(c);
    }

    void showOfferDetails(String name) {
        baseScreen();
        addHeader(
                tr("تفاصيل العرض","Détails de l'offre","Offer details"),
                false
        );

        ArtView art=new ArtView(this);
        art.type=2;
        root.addView(art,new LinearLayout.LayoutParams(-1,240));

        root.addView(text(name,25,GREEN,true));

        addInfo(tr("السعر","Prix","Price"),"5 - 7 DT/kg");
        addInfo(tr("الكمية","Quantité","Quantity"),"1 - 5 tonnes");
        addInfo(tr("الأصل","Origine","Origin"),
                "Bechni - El Fawar - Kebili");
        addInfo(tr("الجودة","Qualité","Quality"),
                tr("ممتازة","Premium","Premium"));
        addInfo(tr("التغليف","Emballage","Packaging"),
                tr("حسب الطلب","Selon la demande","On request"));

        Button contact=button(
                tr("الاتصال بصاحب العرض",
                        "Contacter le vendeur",
                        "Contact seller"),
                GREEN
        );
        root.addView(contact,new LinearLayout.LayoutParams(-1,56));
        contact.setOnClickListener(v->callPhone());

        Button fav=button(
                tr("♡ إضافة إلى المفضلة",
                        "♡ Ajouter aux favoris",
                        "♡ Add to favorites"),
                BROWN
        );
        root.addView(fav,new LinearLayout.LayoutParams(-1,56));
    }

    void addInfo(String label,String value) {
        TextView t=text(label+" : "+value,16,GRAY,false);
        t.setPadding(5,7,5,7);
        root.addView(t);
    }

    void showSell() {
        baseScreen();

        addHeader(
                tr("نشر عرض بيع","Publier une offre","Publish sell offer"),
                true
        );

        field(tr("اسم البائع","Nom du vendeur","Seller name"));
        field(tr("الكمية","Quantité","Quantity"));
        field(tr("السعر","Prix","Price"));
        field(tr("رقم الهاتف","Téléphone","Phone"));
        field(tr("المنطقة","Région","Region"));

        EditText description=field(
                tr("وصف المنتج",
                        "Description du produit",
                        "Product description")
        );
        description.setMinHeight(100);

        Button photo=button(
                tr("📷 إضافة صورة",
                        "📷 Ajouter une photo",
                        "📷 Add photo"),
                BROWN
        );
        root.addView(photo,new LinearLayout.LayoutParams(-1,55));
        photo.setOnClickListener(v->chooseImage());

        Button publish=button(
                tr("نشر العرض","Publier l'offre","Publish offer"),
                GREEN
        );
        root.addView(publish,new LinearLayout.LayoutParams(-1,58));

        publish.setOnClickListener(v->Toast.makeText(
                this,
                tr("تم نشر العرض بنجاح",
                        "Offre publiée avec succès",
                        "Offer published successfully"),
                Toast.LENGTH_LONG
        ).show());

        addBottomNav(2);
    }

    EditText field(String hint) {
        EditText e=new EditText(this);
        e.setHint(hint);
        e.setPadding(18,0,18,0);

        GradientDrawable bg=new GradientDrawable();
        bg.setColor(WHITE);
        bg.setCornerRadius(18);
        bg.setStroke(1,Color.rgb(225,216,203));
        e.setBackground(bg);

        LinearLayout.LayoutParams lp=
                new LinearLayout.LayoutParams(-1,55);
        lp.setMargins(0,6,0,6);

        root.addView(e,lp);
        return e;
    }

    void showOrders() {
        baseScreen();
        addHeader(tr("الطلبات","Commandes","Orders"),true);

        LinearLayout tabs=new LinearLayout(this);

        String[] ts={
                tr("الكل","Toutes","All"),
                tr("قيد التنفيذ","En cours","In progress"),
                tr("مستلمة","Reçues","Received")
        };

        for(String s:ts) {
            TextView t=text(s,13,GREEN,true);
            t.setGravity(Gravity.CENTER);
            tabs.addView(t,new LinearLayout.LayoutParams(0,50,1));
        }

        root.addView(tabs);

        addOrder(
                tr("طلب دقلة نور",
                        "Commande Deglet Nour",
                        "Deglet Nour order"),
                "2 tonnes",
                "05/10/2026",
                tr("قيد التنفيذ","En cours","In progress")
        );

        addOrder(
                tr("طلب دقلة نور ممتازة",
                        "Commande Premium",
                        "Premium order"),
                "5 tonnes",
                "03/10/2026",
                tr("مستلمة","Reçue","Received")
        );

        addBottomNav(3);
    }

    void addOrder(
            String title,String quantity,
            String date,String status) {

        LinearLayout c=card();

        c.addView(text(title,18,GREEN,true));
        c.addView(text(
                quantity+"   •   "+date,
                14,BROWN,false
        ));
        c.addView(text(
                tr("الحالة","Statut","Status")+": "+status,
                14,GRAY,true
        ));

        root.addView(c);
    }

    void showContact() {
        baseScreen();

        addHeader(
                tr("اتصل بنا","Contact","Contact"),
                true
        );

        ArtView art=new ArtView(this);
        art.type=3;
        root.addView(art,new LinearLayout.LayoutParams(-1,190));

        root.addView(text(
                "DATRIDHA",
                28,GREEN,true
        ));

        root.addView(text(
                tr("دقلة النور من بشني",
                        "Deglet Nour de Bechni",
                        "Deglet Nour from Bechni"),
                18,BROWN,true
        ));

        Button phone=button(
                "📞 51 022 448",
                GREEN
        );
        root.addView(phone,new LinearLayout.LayoutParams(-1,55));
        phone.setOnClickListener(v->callPhone());

        Button email=button(
                "✉ ridhatouil1992@gmail.com",
                BROWN
        );
        root.addView(email,new LinearLayout.LayoutParams(-1,55));
        email.setOnClickListener(v->sendEmail());

        Button settings=button(
                tr("الإعدادات","Paramètres","Settings"),
                GREEN
        );
        root.addView(settings,new LinearLayout.LayoutParams(-1,55));
        settings.setOnClickListener(v->showSettings());

        addBottomNav(4);
    }

    void showSettings() {
        baseScreen();

        addHeader(
                tr("الإعدادات","Paramètres","Settings"),
                false
        );

        settingRow(
                tr("اللغة","Langue","Language"),
                tr("العربية / Français / English",
                        "Arabe / Français / Anglais",
                        "Arabic / French / English")
        );

        settingRow(
                tr("الإشعارات","Notifications","Notifications"),
                tr("مفعلة","Activées","Enabled")
        );

        settingRow(
                tr("عن DATRIDHA","À propos de DATRIDHA","About DATRIDHA"),
                tr("تجارة مباشرة لدقلة النور",
                        "Commerce direct de Deglet Nour",
                        "Direct Deglet Nour trade")
        );

        settingRow(
                tr("الخصوصية","Confidentialité","Privacy"),
                tr("بياناتك محمية",
                        "Vos données sont protégées",
                        "Your data is protected")
        );

        settingRow(
                tr("المساعدة","Aide","Help"),
                tr("نحن هنا لمساعدتك",
                        "Nous sommes là pour vous aider",
                        "We are here to help")
        );

        Button lang=button(
                tr("تغيير اللغة","Changer la langue","Change language"),
                GREEN
        );
        root.addView(lang,new LinearLayout.LayoutParams(-1,56));
        lang.setOnClickListener(v->changeLanguage());
    }

    void settingRow(String title,String value) {
        LinearLayout c=card();
        c.addView(text(title,17,GREEN,true));
        c.addView(text(value,14,GRAY,false));
        root.addView(c);
    }

    void changeLanguage() {
        if("ar".equals(language)) language="fr";
        else if("fr".equals(language)) language="en";
        else language="ar";

        showSettings();
    }

    void addProductCard(
            String name,String price,
            String unit,String region) {

        LinearLayout c=card();

        ArtView art=new ArtView(this);
        art.type=2;
        c.addView(art,new LinearLayout.LayoutParams(-1,115));

        c.addView(text(name,18,GREEN,true));

        c.addView(text(
                price+" "+unit+"   •   "+region,
                14,BROWN,false
        ));

        Button b=button(
                tr("عرض التفاصيل","Voir les détails","View details"),
                GREEN
        );
        c.addView(b,new LinearLayout.LayoutParams(-1,48));
        b.setOnClickListener(v->showOfferDetails(name));

        root.addView(c);
    }

    void addHeader(String title,boolean showMenu) {
        LinearLayout h=new LinearLayout(this);
        h.setGravity(Gravity.CENTER_VERTICAL);
        h.setPadding(8,5,8,5);

        if(showMenu) {
            Button menu=button("☰",GREEN);
            h.addView(menu,new LinearLayout.LayoutParams(52,52));
            menu.setOnClickListener(v->showSettings());
        }

        TextView t=text(title,21,GREEN,true);
        t.setGravity(Gravity.CENTER);
        h.addView(t,new LinearLayout.LayoutParams(0,58,1));

        TextView logo=text("DATRIDHA",17,BROWN,true);
        logo.setGravity(Gravity.CENTER);
        h.addView(logo,new LinearLayout.LayoutParams(95,58));

        root.addView(h);
    }

    void addBottomNav(int selected) {
        LinearLayout nav=new LinearLayout(this);
        nav.setGravity(Gravity.CENTER);
        nav.setPadding(3,5,3,5);

        String[] labels={
                tr("الرئيسية","Accueil","Home"),
                tr("شراء","Acheter","Buy"),
                tr("بيع","Vendre","Sell"),
                tr("الطلبات","Commandes","Orders"),
                tr("اتصال","Contact","Contact")
        };

        for(int i=0;i<labels.length;i++) {
            Button b=button(labels[i],
                    i==selected ? GREEN : BROWN);
            b.setTextSize(11);
            b.setAllCaps(false);

            final int index=i;

            b.setOnClickListener(v->{
                if(index==0) showHome();
                else if(index==1) showBuy();
                else if(index==2) showSell();
                else if(index==3) showOrders();
                else showContact();
            });

            nav.addView(b,new LinearLayout.LayoutParams(0,55,1));
        }

        root.addView(nav);
    }

    void baseScreen() {
        root=new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setPadding(12,8,12,8);
        root.setBackgroundColor(CREAM);

        ScrollView scroll=new ScrollView(this);
        scroll.setFillViewport(true);

        LinearLayout content=new LinearLayout(this);
        content.setOrientation(LinearLayout.VERTICAL);

        scroll.addView(content);
        setContentView(scroll);

        root=content;
    }

    TextView text(
            String value,int size,
            int color,boolean bold) {

        TextView t=new TextView(this);
        t.setText(value);
        t.setTextSize(size);
        t.setTextColor(color);
        t.setPadding(4,4,4,4);

        if(bold)
            t.setTypeface(Typeface.DEFAULT,Typeface.BOLD);

        return t;
    }

    Button button(String title,int color) {
        Button b=new Button(this);
        b.setText(title);
        b.setTextColor(WHITE);
        b.setTextSize(15);
        b.setAllCaps(false);

        GradientDrawable bg=new GradientDrawable();
        bg.setColor(color);
        bg.setCornerRadius(22);

        b.setBackground(bg);
        b.setPadding(8,4,8,4);

        return b;
    }

    LinearLayout card() {
        LinearLayout c=new LinearLayout(this);
        c.setOrientation(LinearLayout.VERTICAL);
        c.setPadding(15,15,15,15);

        GradientDrawable bg=new GradientDrawable();
        bg.setColor(WHITE);
        bg.setCornerRadius(20);
        bg.setStroke(1,Color.rgb(232,224,212));
        c.setBackground(bg);

        LinearLayout.LayoutParams lp=
                new LinearLayout.LayoutParams(-1,-2);
        lp.setMargins(0,8,0,8);

        c.setLayoutParams(lp);

        return c;
    }

    void chooseImage() {
        Intent intent=new Intent(
                Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        );
        startActivityForResult(intent,100);
    }

    @Override
    protected void onActivityResult(
            int requestCode,int resultCode,Intent data) {

        super.onActivityResult(
                requestCode,resultCode,data
        );

        if(requestCode==100 && resultCode==RESULT_OK) {
            Toast.makeText(
                    this,
                    tr("تم اختيار الصورة",
                            "Image sélectionnée",
                            "Image selected"),
                    Toast.LENGTH_SHORT
            ).show();
        }
    }

    void callPhone() {
        try {
            Intent i=new Intent(
                    Intent.ACTION_DIAL,
                    Uri.parse("tel:51022448")
            );
            startActivity(i);
        } catch(Exception e) {}
    }

    void sendEmail() {
        try {
            Intent i=new Intent(Intent.ACTION_SENDTO);
            i.setData(Uri.parse(
                    "mailto:ridhatouil1992@gmail.com"
            ));
            i.putExtra(
                    Intent.EXTRA_SUBJECT,
                    "DATRIDHA"
            );
            startActivity(i);
        } catch(Exception e) {}
    }

    class ArtView extends View {

        Paint p=new Paint(Paint.ANTI_ALIAS_FLAG);
        int type=0;

        ArtView(Context c) {
            super(c);
        }

        @Override
        protected void onDraw(Canvas c) {
            super.onDraw(c);

            int w=getWidth();
            int h=getHeight();

            if(type==0) drawSplash(c,w,h);
            else if(type==1) drawOasis(c,w,h);
            else if(type==2) drawDates(c,w,h);
            else drawContactArt(c,w,h);
        }

        void drawSplash(Canvas c,int w,int h) {
            p.setShader(new LinearGradient(
                    0,0,0,h,
                    Color.rgb(8,45,25),
                    Color.rgb(71,145,88),
                    Shader.TileMode.CLAMP
            ));
            c.drawRect(0,0,w,h,p);
            p.setShader(null);

            p.setColor(Color.rgb(255,190,70));
            c.drawCircle(w/2f,65,28,p);

            drawPalm(c,w*.18f,h*.70f,45);
            drawPalm(c,w*.82f,h*.70f,55);

            p.setColor(GOLD);
            for(int i=0;i<8;i++) {
                float x=w*.25f+i*w*.07f;
                c.drawCircle(x,h*.75f,10,p);
            }
        }

        void drawOasis(Canvas c,int w,int h) {
            p.setShader(new LinearGradient(
                    0,0,0,h,
                    Color.rgb(220,190,125),
                    Color.rgb(70,135,75),
                    Shader.TileMode.CLAMP
            ));
            c.drawRect(0,0,w,h,p);
            p.setShader(null);

            p.setColor(Color.rgb(255,220,130));
            c.drawCircle(w*.78f,h*.25f,38,p);

            drawPalm(c,w*.12f,h*.70f,55);
            drawPalm(c,w*.82f,h*.65f,65);
            drawPalm(c,w*.55f,h*.78f,45);

            p.setColor(Color.rgb(32,105,61));
            c.drawRect(0,h*.80f,w,h,p);
        }

        void drawDates(Canvas c,int w,int h) {
            p.setColor(IVORY);
            c.drawRect(0,0,w,h,p);

            p.setColor(Color.rgb(110,68,30));

            for(int r=0;r<3;r++) {
                for(int i=0;i<6;i++) {
                    float x=35+i*48;
                    float y=35+r*35;

                    c.drawOval(
                            x,y,x+35,y+23,p
                    );
                }
            }

            p.setColor(GOLD);
            c.drawCircle(w*.50f,h*.78f,22,p);
        }

        void drawContactArt(Canvas c,int w,int h) {
            p.setColor(GREEN);
            c.drawCircle(w/2f,h*.42f,55,p);

            p.setColor(GOLD);
            c.drawCircle(w/2f,h*.42f,32,p);

            p.setColor(WHITE);
            p.setTextSize(30);
            p.setTextAlign(Paint.Align.CENTER);
            c.drawText("D",w/2f,h*.52f,p);
            p.setTextAlign(Paint.Align.LEFT);
        }

        void drawPalm(Canvas c,float x,float y,float r) {
            p.setColor(BROWN);
            p.setStrokeWidth(9);
            c.drawLine(x,y,x,y-r*1.6f,p);

            p.setColor(LIGHT_GREEN);

            for(int i=0;i<7;i++) {
                double a=(-Math.PI*.9)+(i*Math.PI*1.8/6);
                float ex=x+(float)Math.cos(a)*r;
                float ey=y-r*1.55f+(float)Math.sin(a)*r*.55f;
                c.drawLine(x,y-r*1.55f,ex,ey,p);
            }

            p.setColor(GOLD);
            c.drawCircle(x,y-r*1.55f,8,p);
         }
    }

    @Override
    public void onBackPressed() {
        showHome();
    }
}
