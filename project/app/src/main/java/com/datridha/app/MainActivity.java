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

public class MainActivity extends Activity {

    private LinearLayout root;
    private boolean french = false;

    private final int GREEN = Color.rgb(23,107,58);
    private final int LIGHT_GREEN = Color.rgb(46,139,87);
    private final int GOLD = Color.rgb(212,175,55);
    private final int BROWN = Color.rgb(91,58,30);
    private final int BEIGE = Color.rgb(247,233,215);
    private final int WHITE = Color.WHITE;
    private final int DARK = Color.rgb(45,45,45);

    private ArrayList<String> orders = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showSplash();
    }

    private TextView tv(String text, float size, int color, boolean bold) {
        TextView t = new TextView(this);
        t.setText(text);
        t.setTextSize(size);
        t.setTextColor(color);
        t.setGravity(Gravity.CENTER);
        t.setPadding(16,12,16,12);
        if (bold) t.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        return t;
    }

    private GradientDrawable bg(int color, float radius) {
        GradientDrawable g = new GradientDrawable();
        g.setColor(color);
        g.setCornerRadius(radius);
        return g;
    }

    private Button button(String text, int color) {
        Button b = new Button(this);
        b.setText(text);
        b.setTextSize(16);
        b.setTextColor(WHITE);
        b.setAllCaps(false);
        b.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        b.setBackground(bg(color, 30));
        b.setPadding(15,8,15,8);

        LinearLayout.LayoutParams p =
                new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
        p.setMargins(0,8,0,8);
        b.setLayoutParams(p);
        return b;
    }

    private TextView cardText(String text) {
        TextView t = tv(text, 16, DARK, false);
        t.setGravity(Gravity.CENTER);
        t.setBackground(bg(WHITE, 22));

        LinearLayout.LayoutParams p =
                new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
        p.setMargins(0,7,0,7);
        t.setLayoutParams(p);
        return t;
    }

    private EditText input(String hint) {
        EditText e = new EditText(this);
        e.setHint(hint);
        e.setTextSize(16);
        e.setTextColor(DARK);
        e.setHintTextColor(Color.GRAY);
        e.setSingleLine(false);
        e.setPadding(18,14,18,14);
        e.setBackground(bg(WHITE,20));

        LinearLayout.LayoutParams p =
                new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
        p.setMargins(0,6,0,6);
        e.setLayoutParams(p);
        return e;
    }

    private void base(String title, String subtitle) {
        ScrollView scroll = new ScrollView(this);

        root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setPadding(18,16,18,90);
        root.setBackgroundColor(BEIGE);

        scroll.addView(root);
        setContentView(scroll);

        LinearLayout header = new LinearLayout(this);
        header.setGravity(Gravity.CENTER_VERTICAL);
        header.setPadding(8,8,8,8);
        header.setBackground(bg(GREEN,22));

        TextView logo = tv("🌴 DATRIDHA",20,WHITE,true);
        logo.setGravity(Gravity.CENTER);

        header.addView(logo,new LinearLayout.LayoutParams(
                0,ViewGroup.LayoutParams.WRAP_CONTENT,1));

        Button lang = new Button(this);
        lang.setText(french ? "عربي" : "FR");
        lang.setTextColor(WHITE);
        lang.setAllCaps(false);
        lang.setBackground(bg(LIGHT_GREEN,20));
        lang.setOnClickListener(v -> {
            french = !french;
            showHome();
        });

        header.addView(lang,new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));

        root.addView(header);

        TextView h = tv(title,28,GREEN,true);
        h.setPadding(8,22,8,5);
        root.addView(h);

        TextView sub = tv(subtitle,15,BROWN,false);
        root.addView(sub);
    }

    private void addBottomNav() {
        Space space = new Space(this);
        root.addView(space,new LinearLayout.LayoutParams(
                1,18));

        LinearLayout nav = new LinearLayout(this);
        nav.setOrientation(LinearLayout.HORIZONTAL);
        nav.setGravity(Gravity.CENTER);
        nav.setPadding(4,4,4,4);
        nav.setBackground(bg(GREEN,25));

        Button home = navButton(french ? "Accueil" : "الرئيسية");
        Button buy = navButton(french ? "Acheter" : "شراء");
        Button order = navButton(french ? "Commandes" : "الطلبات");
        Button contact = navButton(french ? "Contact" : "تواصل");

        home.setOnClickListener(v -> showHome());
        buy.setOnClickListener(v -> showBuy());
        order.setOnClickListener(v -> showOrders());
        contact.setOnClickListener(v -> showContact());

        nav.addView(home,new LinearLayout.LayoutParams(0,
                ViewGroup.LayoutParams.WRAP_CONTENT,1));
        nav.addView(buy,new LinearLayout.LayoutParams(0,
                ViewGroup.LayoutParams.WRAP_CONTENT,1));
        nav.addView(order,new LinearLayout.LayoutParams(0,
                ViewGroup.LayoutParams.WRAP_CONTENT,1));
        nav.addView(contact,new LinearLayout.LayoutParams(0,
                ViewGroup.LayoutParams.WRAP_CONTENT,1));

        root.addView(nav);
    }

    private Button navButton(String text) {
        Button b = new Button(this);
        b.setText(text);
        b.setTextSize(12);
        b.setTextColor(WHITE);
        b.setAllCaps(false);
        b.setBackgroundColor(Color.TRANSPARENT);
        return b;
    }

    private void showSplash() {
        LinearLayout l = new LinearLayout(this);
        l.setOrientation(LinearLayout.VERTICAL);
        l.setGravity(Gravity.CENTER);
        l.setPadding(30,30,30,30);
        l.setBackgroundColor(GREEN);

        TextView palm = tv("🌴",70,WHITE,true);
        l.addView(palm);

        TextView logo = tv("DATRIDHA",42,GOLD,true);
        l.addView(logo);

        TextView name = tv(
                french ? "Deglet Nour de Bechni" :
                        "دقلة النور من بشني",
                20,WHITE,true);
        l.addView(name);

        TextView desc = tv(
                french ? "Commerce direct de gros" :
                        "تجارة مباشرة بالجملة",
                16,WHITE,false);
        l.addView(desc);

        Button start = button(
                french ? "Commencer" : "ابدأ",
                GOLD);
        start.setTextColor(BROWN);
        start.setOnClickListener(v -> showHome());

        LinearLayout.LayoutParams p =
                new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
        p.setMargins(30,30,30,10);
        l.addView(start,p);

        setContentView(l);
    }

    private void showHome() {
        base(
                "🌴 DATRIDHA",
                french ?
                        "Deglet Nour de Bechni • Commerce de gros" :
                        "دقلة النور من بشني • تجارة بالجملة");

        TextView welcome = tv(
                french ?
                        "Bienvenue sur DATRIDHA" :
                        "مرحبًا بك في DATRIDHA",
                22,GREEN,true);
        root.addView(welcome);

        TextView info = cardText(
                french ?
                        "Achetez et vendez la Deglet Nour directement avec les producteurs." :
                        "اشترِ وبِع دقلة النور مباشرة مع المنتجين.");
        root.addView(info);

        Button buy = button(
                "🛒 " + (french ? "Acheter Deglet Nour" :
                        "شراء دقلة النور"),
                GREEN);
        buy.setOnClickListener(v -> showBuy());
        root.addView(buy);

        Button sell = button(
                "📦 " + (french ? "Vendre Deglet Nour" :
                        "بيع دقلة النور"),
                GOLD);
        sell.setTextColor(BROWN);
        sell.setOnClickListener(v -> showSell());
        root.addView(sell);

        Button ordersBtn = button(
                "📋 " + (french ? "Commandes" : "الطلبات"),
                Color.rgb(52,102,160));
        ordersBtn.setOnClickListener(v -> showOrders());
        root.addView(ordersBtn);

        Button contact = button(
                "☎ " + (french ? "Contact" : "تواصل معنا"),
                BROWN);
        contact.setOnClickListener(v -> showContact());
        root.addView(contact);

        TextView products = tv(
                french ? "Deglet Nour • Bechni" :
                        "دقلة النور • بشني",
                21,BROWN,true);
        root.addView(products);

        root.addView(cardText(
                french ?
                        "Qualité • Origine Bechni • Vente en gros" :
                        "جودة • منشأ بشني • بيع بالجملة"));

        root.addView(cardText(
                "✓ " + (french ? "Fiable" : "موثوق") +
                "    ✓ " + (french ? "Qualité" : "جودة")));

        root.addView(cardText(
                "✓ " + (french ? "Commerce direct" : "تجارة مباشرة") +
                "    ✓ " + (french ? "Soutien aux producteurs" :
                        "دعم المنتجين")));

        Button settings = button(
                "⚙ " + (french ? "Paramètres" : "الإعدادات"),
                Color.DKGRAY);
        settings.setOnClickListener(v -> showSettings());
        root.addView(settings);

        addBottomNav();
    }

    private void showBuy() {
        base(
                french ? "Acheter Deglet Nour" :
                        "شراء دقلة النور",
                french ?
                        "Offres disponibles en gros" :
                        "العروض المتوفرة للبيع بالجملة");

        EditText search = input(
                french ? "Rechercher une offre..." :
                        "ابحث عن عرض...");
        root.addView(search);

        TextView filters = cardText(
                french ?
                        "Filtres : Toutes • Quantité • Prix • Région" :
                        "الفلاتر: الكل • الكمية • السعر • المنطقة");
        root.addView(filters);

        addOfferCard(
                "🌴 Deglet Nour - Bechni",
                french ?
                        "Quantité : 5 tonnes\nPrix : après contact\nRégion : Bechni - Kebili" :
                        "الكمية: 5 طن\nالسعر: بعد التواصل\nالمنطقة: بشني - قبلي");

        addOfferCard(
                "🌴 Deglet Nour - Qualité Premium",
                french ?
                        "Quantité : 2 tonnes\nPrix : après contact\nRégion : Kebili" :
                        "الكمية: 2 طن\nالسعر: بعد التواصل\nالمنطقة: قبلي");

        addOfferCard(
                "🌴 Deglet Nour - Vente directe",
                french ?
                        "Quantité : 1 tonne\nPrix : après contact\nRégion : Bechni" :
                        "الكمية: 1 طن\nالسعر: بعد التواصل\nالمنطقة: بشني");

        addBottomNav();
    }

    private void addOfferCard(String title, String details) {
        LinearLayout card = new LinearLayout(this);
        card.setOrientation(LinearLayout.VERTICAL);
        card.setPadding(16,16,16,16);
        card.setBackground(bg(WHITE,24));

        LinearLayout.LayoutParams cp =
                new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
        cp.setMargins(0,8,0,8);
        card.setLayoutParams(cp);

        TextView t = tv(title,19,GREEN,true);
        card.addView(t);

        TextView d = tv(details,15,DARK,false);
        d.setGravity(Gravity.CENTER);
        card.addView(d);

        Button detailsBtn = button(
                french ? "Voir les détails" : "عرض التفاصيل",
                GREEN);

        detailsBtn.setOnClickListener(v -> showOfferDetails(title,details));
        card.addView(detailsBtn);

        root.addView(card);
    }

    private void showOfferDetails(String title,String details) {
        base(
                french ? "Détails de l'offre" :
                        "تفاصيل العرض",
                title);

        TextView image = tv("🌴🌴\n🌴  DATRIDHA  🌴\n🌴🌴",
                25,GREEN,true);
        image.setBackground(bg(WHITE,25));
        root.addView(image);

        root.addView(cardText(details));

        root.addView(cardText(
                french ?
                        "Origine : Bechni\nQualité : Deglet Nour\nConditionnement : selon accord" :
                        "المنشأ: بشني\nالجودة: دقلة النور\nالتعبئة: حسب الاتفاق"));

        Button favorite = button(
                "♡ " + (french ? "Ajouter aux favoris" :
                        "إضافة إلى المفضلة"),
                GOLD);
        favorite.setTextColor(BROWN);
        favorite.setOnClickListener(v ->
                Toast.makeText(this,
                        french ? "Ajouté aux favoris" :
                                "تمت الإضافة إلى المفضلة",
                        Toast.LENGTH_SHORT).show());
        root.addView(favorite);

        Button contact = button(
                "☎ " + (french ? "Contacter le vendeur" :
                        "التواصل مع صاحب العرض"),
                GREEN);
        contact.setOnClickListener(v -> showContact());
        root.addView(contact);

        Button order = button(
                "🛒 " + (french ? "Demander cet offre" :
                        "طلب هذا العرض"),
                BROWN);
        order.setOnClickListener(v -> {
            orders.add(title);
            Toast.makeText(this,
                    french ? "Demande enregistrée" :
                            "تم تسجيل الطلب",
                    Toast.LENGTH_SHORT).show();
            showOrders();
        });
        root.addView(order);

        addBottomNav();
    }

    private void showSell() {
        base(
                french ? "Vendre Deglet Nour" :
                        "بيع دقلة النور",
                french ?
                        "Publiez votre offre de vente en gros" :
                        "انشر عرضك للبيع بالجملة");

        EditText name = input(
                french ? "Nom du vendeur / société" :
                        "اسم البائع / الشركة");
        root.addView(name);

        EditText quantity = input(
                french ? "Quantité disponible (kg / tonnes)" :
                        "الكمية المتوفرة (كغ / طن)");
        root.addView(quantity);

        EditText price = input(
                french ? "Prix demandé (DT / kg)" :
                        "السعر المطلوب (د.ت / كغ)");
        root.addView(price);

        EditText phone = input(
                french ? "Numéro de téléphone" :
                        "رقم الهاتف");
        root.addView(phone);

        EditText description = input(
                french ?
                        "Description : qualité, conditionnement, conditions..." :
                        "الوصف: الجودة، التعبئة، شروط البيع...");
        root.addView(description);

        Button photo = button(
                "📷 " + (french ? "Ajouter des photos" :
                        "إضافة صور"),
                Color.rgb(110,70,130));
        photo.setOnClickListener(v -> {
            Intent i = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            i.setType("image/*");
            i.putExtra(Intent.EXTRA_ALLOW_MULTIPLE,true);
            i.addCategory(Intent.CATEGORY_OPENABLE);
            try {
                startActivityForResult(i,100);
            } catch(Exception e) {
                Toast.makeText(this,
                        french ? "Impossible d'ouvrir les photos" :
                                "تعذر فتح الصور",
                        Toast.LENGTH_SHORT).show();
            }
        });
        root.addView(photo);

        Button publish = button(
                "✓ " + (french ? "Publier l'offre" :
                        "نشر العرض"),
                GREEN);

        publish.setOnClickListener(v -> {
            if (name.getText().toString().trim().isEmpty() ||
                quantity.getText().toString().trim().isEmpty() ||
                phone.getText().toString().trim().isEmpty()) {

                Toast.makeText(this,
                        french ?
                                "Veuillez remplir les champs obligatoires" :
                                "يرجى ملء الخانات الإلزامية",
                        Toast.LENGTH_LONG).show();
                return;
            }

            Toast.makeText(this,
                    french ?
                            "Votre offre a été enregistrée" :
                            "تم تسجيل عرضك بنجاح",
                    Toast.LENGTH_LONG).show();

            showHome();
        });

        root.addView(publish);

        addBottomNav();
    }

    private void showOrders() {
        base(
                french ? "Commandes" : "الطلبات",
                french ?
                        "Historique et suivi de vos demandes" :
                        "متابعة وتاريخ طلباتك");

        root.addView(cardText(
                french ?
                        "Filtres : Toutes • En cours • Reçues" :
                        "الفلاتر: الكل • قيد التنفيذ • مستلمة"));

        if (orders.size() == 0) {
            root.addView(cardText(
                    french ?
                            "Aucune commande pour le moment." :
                            "لا توجد طلبات حاليًا."));
        } else {
            for (String order : orders) {
                root.addView(cardText(
                        "🌴 " + order +
                        "\n" +
                        (french ?
                                "Statut : En attente" :
                                "الحالة: في الانتظار")));
            }
        }

        root.addView(cardText(
                "🔵 " + (french ? "En cours" : "قيد التنفيذ") +
                "\n🟢 " + (french ? "Acceptée" : "مقبولة") +
                "\n🟡 " + (french ? "En attente" : "في الانتظار")));

        addBottomNav();
    }

    private void showContact() {
        base(
                french ? "Contact" : "تواصل معنا",
                french ?
                        "L'équipe DATRIDHA est à votre disposition"
