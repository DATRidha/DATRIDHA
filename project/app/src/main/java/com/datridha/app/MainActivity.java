package com.datridha.app;

import android.app.Activity;
import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.View;
import android.widget.*;

public class MainActivity extends Activity {

    // =========================
    // COLORS
    // =========================

    static final int GREEN = Color.rgb(23,107,58);
    static final int GREEN2 = Color.rgb(46,139,87);
    static final int GOLD = Color.rgb(212,175,55);
    static final int BROWN = Color.rgb(91,58,30);
    static final int CREAM = Color.rgb(249,244,236);
    static final int IVORY = Color.rgb(247,233,215);
    static final int WHITE = Color.WHITE;
    static final int GRAY = Color.rgb(102,102,102);
    static final int LIGHT = Color.rgb(245,241,232);

    LinearLayout root;

    String language = "ar";
    String currentPage = "splash";

    // =========================
    // TRANSLATION
    // =========================

    String tr(String ar, String fr, String en) {
        if ("fr".equals(language)) return fr;
        if ("en".equals(language)) return en;
        return ar;
    }

    // =========================
    // CREATE
    // =========================

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showSplash();
    }

    // =========================================================
    // SPLASH — FIRST SCREEN
    // =========================================================

    void showSplash() {

        currentPage = "splash";

        FrameLayout frame = new FrameLayout(this);

        ImageView background = new ImageView(this);
        background.setImageResource(R.drawable.splash_oasis);
        background.setScaleType(ImageView.ScaleType.CENTER_CROP);

        frame.addView(
                background,
                new FrameLayout.LayoutParams(
                        -1,
                        -1
                )
        );

        View dark = new View(this);

        GradientDrawable overlay =
                new GradientDrawable(
                        GradientDrawable.Orientation.TOP_BOTTOM,
                        new int[]{
                                Color.argb(25,0,0,0),
                                Color.argb(45,0,0,0),
                                Color.argb(120,0,45,20)
                        }
                );

        dark.setBackground(overlay);

        frame.addView(
                dark,
                new FrameLayout.LayoutParams(
                        -1,
                        -1
                )
        );

        LinearLayout content = new LinearLayout(this);

        content.setOrientation(
                LinearLayout.VERTICAL
        );

        content.setGravity(
                Gravity.CENTER_HORIZONTAL
        );

        content.setPadding(
                28,
                45,
                28,
                28
        );

        FrameLayout.LayoutParams cp =
                new FrameLayout.LayoutParams(
                        -1,
                        -1
                );

        frame.addView(content, cp);

        Space top = new Space(this);

        content.addView(
                top,
                new LinearLayout.LayoutParams(
                        1,
                        25
                )
        );

        // LOGO

        ImageView logo =
                new ImageView(this);

        logo.setImageResource(
                R.drawable.app_icon
        );

        logo.setScaleType(
                ImageView.ScaleType.CENTER_INSIDE
        );

        content.addView(
                logo,
                new LinearLayout.LayoutParams(
                        -1,
                        175
                )
        );

        TextView arabicTitle =
                text(
                        "دقلة النور من بشني",
                        23,
                        WHITE,
                        true
                );

        arabicTitle.setGravity(
                Gravity.CENTER
        );

        content.addView(arabicTitle);

        TextView frenchTitle =
                text(
                        "Deglet Nour de Bechni",
                        17,
                        WHITE,
                        true
                );

        frenchTitle.setGravity(
                Gravity.CENTER
        );

        content.addView(frenchTitle);

        TextView slogan =
                text(
                        "Achetez • Vendez • Échangez",
                        15,
                        WHITE,
                        false
                );

        slogan.setGravity(
                Gravity.CENTER
        );

        content.addView(slogan);

        Space middle = new Space(this);

        content.addView(
                middle,
                new LinearLayout.LayoutParams(
                        1,
                        25
                )
        );

        // START BUTTON

        Button start =
                bigButton(
                        tr(
                                "ابدأ الآن",
                                "Commencer",
                                "Start now"
                        ),
                        GREEN
                );

        content.addView(
                start,
                new LinearLayout.LayoutParams(
                        -1,
                        58
                )
        );

        start.setOnClickListener(
                v -> showHome()
        );

        Space langSpace = new Space(this);

        content.addView(
                langSpace,
                new LinearLayout.LayoutParams(
                        1,
                        12
                )
        );

        // LANGUAGES

        LinearLayout languages =
                new LinearLayout(this);

        languages.setGravity(
                Gravity.CENTER
        );

        addLanguage(
                languages,
                "🇫🇷 Français",
                "fr"
        );

        addLanguage(
                languages,
                "🇹🇳 العربية",
                "ar"
        );

        addLanguage(
                languages,
                "🇬🇧 English",
                "en"
        );

        content.addView(
                languages,
                new LinearLayout.LayoutParams(
                        -1,
                        48
                )
        );

        setContentView(frame);
    }

    void addLanguage(
            LinearLayout parent,
            String title,
            String lang) {

        Button b =
                new Button(this);

        b.setText(title);
        b.setTextSize(12);
        b.setTextColor(BROWN);
        b.setAllCaps(false);

        GradientDrawable bg =
                new GradientDrawable();

        bg.setColor(Color.WHITE);
        bg.setCornerRadius(30);

        b.setBackground(bg);

        LinearLayout.LayoutParams lp =
                new LinearLayout.LayoutParams(
                        0,
                        44,
                        1
                );

        lp.setMargins(
                4,
                2,
                4,
                2
        );

        parent.addView(b, lp);

        b.setOnClickListener(
                v -> {
                    language = lang;
                    showSplash();
                }
        );
    }

    // =========================================================
    // HOME
    // =========================================================

    void showHome() {

        currentPage = "home";

        baseScreen();

        addTopBar(
                tr(
                        "الرئيسية",
                        "Accueil",
                        "Home"
                ),
                true
        );

        ImageView hero =
                image(
                        R.drawable.oasis_home,
                        ImageView.ScaleType.CENTER_CROP
                );

        root.addView(
                hero,
                new LinearLayout.LayoutParams(
                        -1,
                        235
                )
        );

        TextView title =
                text(
                        tr(
                                "دقلة النور من بشني",
                                "Deglet Nour de Bechni",
                                "Deglet Nour from Bechni"
                        ),
                        22,
                        BROWN,
                        true
                );

        title.setGravity(Gravity.CENTER);

        root.addView(title);

        TextView subtitle =
                text(
                        tr(
                                "اشتر • بع • تعامل",
                                "Achetez • Vendez • Échangez",
                                "Buy • Sell • Trade"
                        ),
                        14,
                        GRAY,
                        false
                );

        subtitle.setGravity(Gravity.CENTER);

        root.addView(subtitle);

        // 4 MAIN BUTTONS

        LinearLayout row1 =
                new LinearLayout(this);

        addMainButton(
                row1,
                tr("شراء","Acheter","Buy"),
                GREEN,
                () -> showBuy()
        );

        addMainButton(
                row1,
                tr("بيع","Vendre","Sell"),
                Color.rgb(235,117,10),
                () -> showSell()
        );

        root.addView(row1);

        LinearLayout row2 =
                new LinearLayout(this);

        addMainButton(
                row2,
                tr(
                        "الطلبات",
                        "Commandes",
                        "Orders"
                ),
                BROWN,
                () -> showOrders()
        );

        addMainButton(
                row2,
                tr(
                        "اتصل بنا",
                        "Contact",
                        "Contact"
                ),
                GREEN2,
                () -> showContact()
        );

        root.addView(row2);

        // PRODUCTS

        LinearLayout productTitle =
                new LinearLayout(this);

        productTitle.setGravity(
                Gravity.CENTER_VERTICAL
        );

        TextView ptitle =
                text(
                        tr(
                                "منتجاتنا",
                                "Nos produits",
                                "Our products"
                        ),
                        20,
                        GREEN,
                        true
                );

        productTitle.addView(
                ptitle,
                new LinearLayout.LayoutParams(
                        0,
                        55,
                        1
                )
        );

        TextView more =
                text(
                        tr(
                                "رؤية الكل →",
                                "Voir tout →",
                                "View all →"
                        ),
                        13,
                        BROWN,
                        false
                );

        productTitle.addView(more);

        root.addView(productTitle);

        addHomeProduct(
                tr(
                        "دقلة نور",
                        "Deglet Nour",
                        "Deglet Nour"
                ),
                "5 DT/kg",
                "Bechni"
        );

        addHomeProduct(
                tr(
                        "جودة Premium",
                        "Qualité Premium",
                        "Premium Quality"
                ),
                "6 DT/kg",
                "Kebili"
        );

        addBottomNavigation(0);
    }

    void addMainButton(
            LinearLayout parent,
            String title,
            int color,
            final Runnable action) {

        Button b =
                bigButton(
                        title,
                        color
                );

        LinearLayout.LayoutParams lp =
                new LinearLayout.LayoutParams(
                        0,
                        76,
                        1
                );

        lp.setMargins(
                5,
                5,
                5,
                5
        );

        parent.addView(b, lp);

        b.setOnClickListener(
                v -> action.run()
        );
    }

    void addHomeProduct(
            String name,
            String price,
            String region) {

        LinearLayout card =
                card();

        LinearLayout horizontal =
                new LinearLayout(this);

        horizontal.setOrientation(
                LinearLayout.HORIZONTAL
        );

        ImageView img =
                image(
                        R.drawable.dates_deglet_nour,
                        ImageView.ScaleType.CENTER_CROP
                );

        horizontal.addView(
                img,
                new LinearLayout.LayoutParams(
                        125,
                        100
                )
        );

        LinearLayout info =
                new LinearLayout(this);

        info.setOrientation(
                LinearLayout.VERTICAL
        );

        info.setPadding(
                12,
                2,
                2,
                2
        );

        info.addView(
                text(
                        name,
                        17,
                        GREEN,
                        true
                )
        );

        info.addView(
                text(
                        price,
                        14,
                        BROWN,
                        true
                )
        );

        info.addView(
                text(
                        region,
                        13,
                        GRAY,
                        false
                )
        );

        horizontal.addView(
                info,
                new LinearLayout.LayoutParams(
                        0,
                        -2,
                        1
                )
        );

        card.addView(horizontal);

        root.addView(card);
    }

    // =========================================================
    // BUY
    // =========================================================

    void showBuy() {

        currentPage = "buy";

        baseScreen();

        addTopBar(
                tr(
                        "شراء",
                        "Acheter",
                        "Buy"
                ),
                false
        );

        EditText search =
                new EditText(this);

        search.setHint(
                tr(
                        "ابحث عن عرض...",
                        "Rechercher une offre...",
                        "Search an offer..."
                )
        );

        search.setSingleLine(true);
        search.setTextSize(14);
        search.setPadding(
                18,
                0,
                18,
                0
        );

        GradientDrawable searchBg =
                new GradientDrawable();

        searchBg.setColor(WHITE);
        searchBg.setCornerRadius(25);

        search.setBackground(searchBg);

        root.addView(
                search,
                new LinearLayout.LayoutParams(
                        -1,
                        52
                )
        );

        LinearLayout filters =
                new LinearLayout(this);

        String[] fs = {
                tr("الكل","Tout","All"),
                tr("الكمية","Quantité","Quantity"),
                tr("السعر","Prix","Price"),
                tr("المنطقة","Région","Region")
        };

        for(String s : fs) {

            TextView f =
                    text(
                            s,
                            12,
                            GREEN,
                            true
                    );

            f.setGravity(Gravity.CENTER);

            GradientDrawable fb =
                    new GradientDrawable();

            fb.setColor(WHITE);
            fb.setCornerRadius(22);

            f.setBackground(fb);

            filters.addView(
                    f,
                    new LinearLayout.LayoutParams(
                            0,
                            43,
                            1
                    )
            );
        }

        root.addView(filters);

        addOfferCard(
                "Deglet Nour - Bechni",
                "5 DT/kg",
                "5 tonnes",
                "Bechni"
        );

        addOfferCard(
                "Deglet Nour Premium",
                "6 DT/kg",
                "5 tonnes",
                "El Fawar"
        );

        addOfferCard(
                "Deglet Nour Catégorie A",
                "7 DT/kg",
                "2 tonnes",
                "Kebili"
        );

        addBottomNavigation(1);
    }

    void addOfferCard(
            String name,
            String price,
            String quantity,
            String region) {

        LinearLayout c = card();

        LinearLayout top =
                new LinearLayout(this);

        top.setOrientation(
                LinearLayout.HORIZONTAL
        );

        ImageView img =
                image(
                        R.drawable.dates_deglet_nour,
                        ImageView.ScaleType.CENTER_CROP
                );

        top.addView(
                img,
                new LinearLayout.LayoutParams(
                        115,
                        100
                )
        );

        LinearLayout info =
                new LinearLayout(this);

        info.setOrientation(
                LinearLayout.VERTICAL
        );

        info.setPadding(
                10,
                0,
                0,
                0
        );

        info.addView(
                text(
                        name,
                        16,
                        BROWN,
                        true
                )
        );

        info.addView(
                text(
                        tr("الكمية","Quantité","Quantity")
                                + " : "
                                + quantity,
                        12,
                        GRAY,
                        false
                )
        );

        info.addView(
                text(
                        tr("السعر","Prix","Price")
                                + " : "
                                + price,
                        12,
                        BROWN,
                        false
                )
        );

        info.addView(
                text(
                        tr("المنطقة","Région","Region")
                                + " : "
                                + region,
                        12,
                        GRAY,
                        false
                )
        );

        top.addView(
                info,
                new LinearLayout.LayoutParams(
                        0,
                        -2,
                        1
                )
        );

        c.addView(top);

        Button details =
                smallButton(
                        tr(
                                "Voir détails",
                                "Voir détails",
                                "View details"
                        ),
                        GREEN
                );

        c.addView(
                details,
                new LinearLayout.LayoutParams(
                        -1,
                        48
                )
        );

        details.setOnClickListener(
                v -> showOfferDetails(name)
        );

        root.addView(c);
    }

    // =========================================================
    // OFFER DETAILS
    // =========================================================

    void showOfferDetails(String name) {

        currentPage = "details";

        baseScreen();

        addTopBar(
                tr(
                        "تفاصيل العرض",
                        "Détails de l'offre",
                        "Offer details"
                ),
                false
        );

        ImageView img =
                image(
                        R.drawable.dates_deglet_nour,
                        ImageView.ScaleType.CENTER_CROP
                );

        root.addView(
                img,
                new LinearLayout.LayoutParams(
                        -1,
                        255
                )
        );

        root.addView(
                text(
                        name,
                        24,
                        GREEN,
                        true
                )
        );

        infoRow(
                tr("السعر","Prix","Price"),
                "5 - 7 DT/kg"
        );

        infoRow(
                tr("الكمية","Quantité","Quantity"),
                "1 - 5 tonnes"
        );

        infoRow(
                tr("الأصل","Origine","Origin"),
                "Bechni - El Fawar - Kebili"
        );

        infoRow(
                tr("الجودة","Qualité","Quality"),
                tr("Premium","Premium","Premium")
        );

        infoRow(
                tr(
                        "التغليف",
                        "Conditionnement",
                        "Packaging"
                ),
                tr(
                        "حسب الطلب",
                        "Selon la demande",
                        "On request"
                )
        );

        LinearLayout actions =
                new LinearLayout(this);

        Button fav =
                smallButton(
                        "♡ "
                                + tr(
                                "المفضلة",
                                "Favoris",
                                "Favorite"
                        ),
                        Color.WHITE
                );

        fav.setTextColor(
                Color.rgb(210,60,45)
        );

        Button contact =
                smallButton(
                        tr(
                                "Contact",
                                "Contacter",
                                "Contact seller"
                        ),
                        GREEN
                );

        actions.addView(
                fav,
                new LinearLayout.LayoutParams(
                        0,
                        54,
                        1
                )
        );

        actions.addView(
                contact,
                new LinearLayout.LayoutParams(
                        0,
                        54,
                        1
                )
        );

        root.addView(actions);

        contact.setOnClickListener(
                v -> showContact()
        );
    }

    void infoRow(
            String title,
            String value) {

        LinearLayout row =
                new LinearLayout(this);

        row.setGravity(
                Gravity.CENTER_VERTICAL
        );

        row.setPadding(
                5,
                8,
                5,
                8
        );

        TextView a =
                text(
                        title,
                        14,
                        BROWN,
                        true
                );

        row.addView(
                a,
                new LinearLayout.LayoutParams(
                        0,
                        -2,
                        1
                )
        );

        TextView b =
                text(
                        value,
                        14,
                        GRAY,
                        false
                );

        row.addView(b);

        root.addView(row);
    }

    // =========================================================
    // SELL
    // =========================================================

    void showSell() {

        currentPage = "sell";

        baseScreen();

        addTopBar(
                tr(
                        "نشر عرض بيع",
                        "Publier une offre",
                        "Publish an offer"
                ),
                false
        );

        field(
                tr(
                        "اسم البائع *",
                        "Nom du vendeur *",
                        "Seller name *"
                )
        );

        field(
                tr(
                        "الكمية (كغ / طن) *",
                        "Quantité (kg / tonnes) *",
                        "Quantity (kg / tonnes) *"
                )
        );

        field(
                tr(
                        "السعر المقترح *",
                        "Prix proposé *",
                        "Proposed price *"
                )
        );

        field(
                tr(
                        "رقم الهاتف *",
                        "Numéro de téléphone *",
                        "Phone number *"
                )
        );

        EditText description =
                new EditText(this);

        description.setHint(
                tr(
                        "الوصف / الجودة / التغليف",
                        "Description / qualité / conditionnement",
                        "Description / quality / packaging"
                )
        );

        description.setGravity(
                Gravity.TOP
        );

        description.setMinHeight(100);

        root.addView(
                description,
                new LinearLayout.LayoutParams(
                        -1,
                        105
                )
        );

        Button photos =
                bigButton(
                        "📷  "
                                + tr(
                                "إضافة الصور",
                                "Ajouter des photos",
                                "Add photos"
                        ),
                        IVORY
                );

        photos.setTextColor(GREEN);

        root.addView(
                photos,
                new LinearLayout.LayoutParams(
                        -1,
                        80
                )
        );

        photos.setOnClickListener(
                v -> chooseImage()
        );

        Button publish =
                bigButton(
                        tr(
                                "نشر العرض",
                                "Publier l'offre",
                                "Publish offer"
                        ),
                        GREEN
                );

        root.addView(
                publish,
                new LinearLayout.LayoutParams(
                        -1,
                        58
                )
        );

        publish.setOnClickListener(
                v -> {
                    Toast.makeText(
                            this,
                            tr(
                                    "تم نشر العرض بنجاح",
                                    "Offre publiée avec succès",
                                    "Offer published successfully"
                            ),
                            Toast.LENGTH_SHORT
                    ).show();

                    showOrders();
                }
        );
    }

    void field(String hint) {

        EditText e =
                new EditText(this);

        e.setHint(hint);
        e.setSingleLine(true);
        e.setTextSize(14);
        e.setPadding(
                15,
                0,
                15,
                0
        );

        root.addView(
                e,
                new LinearLayout.LayoutParams(
                        -1,
                        55
                )
        );
    }

    // =========================================================
    // ORDERS
    // =========================================================

    void showOrders() {

        currentPage = "orders";

        baseScreen();

        addTopBar(
                tr(
                        "الطلبات",
                        "Commandes",
                        "Orders"
                ),
                false
        );

        LinearLayout tabs =
                new LinearLayout(this);

        addTab(
                tabs,
                tr("الكل","Tous","All")
        );

        addTab(
                tabs,
                tr(
                        "قيد التنفيذ",
                        "En cours",
                        "In progress"
                )
        );

        addTab(
                tabs,
                tr(
                        "مستلمة",
                        "Reçues",
                        "Received"
                )
        );

        root.addView(tabs);

        orderCard(
                tr(
                        "طلب شراء دقلة النور",
                        "Commande Deglet Nour",
                        "Deglet Nour purchase"
                ),
                "5 tonnes",
                tr(
                        "قيد التنفيذ",
                        "En cours",
                        "In progress"
                )
        );

        orderCard(
                tr(
                        "طلب دقلة نور Premium",
                        "Commande Premium",
                        "Premium order"
                ),
                "2 tonnes",
                tr(
                        "مستلم",
                        "Reçue",
                        "Received"
                )
        );

        addBottomNavigation(3);
    }

    void addTab(
            LinearLayout parent,
            String title) {

        TextView t =
                text(
                        title,
                        12,
                        GREEN,
                        true
                );

        t.setGravity(
                Gravity.CENTER
        );

        GradientDrawable bg =
                new GradientDrawable();

        bg.setColor(WHITE);
        bg.setCornerRadius(22);

        t.setBackground(bg);

        parent.addView(
                t,
                new LinearLayout.LayoutParams(
                        0,
                        45,
                        1
                )
        );
    }

    void orderCard(
            String title,
            String quantity,
            String status) {

        LinearLayout c = card();

        c.addView(
                text(
                        title,
                        17,
                        GREEN,
                        true
                )
        );

        c.addView(
                text(
                        tr(
                                "الكمية",
                                "Quantité",
                                "Quantity"
                        )
                                + " : "
                                + quantity,
                        14,
                        BROWN,
                        false
                )
        );

        TextView s =
                text(
                        status,
                        13,
                        GREEN,
                        true
                );

        c.addView(s);

        root.addView(c);
    }

    // =========================================================
    // CONTACT
    // =========================================================

    void showContact() {

        currentPage = "contact";

        baseScreen();

        addTopBar(
                tr(
                        "اتصل بنا",
                        "Contact",
                        "Contact"
                ),
                false
        );

        ImageView img =
                image(
                        R.drawable.oasis_home,
                        ImageView.ScaleType.CENTER_CROP
                );

        root.addView(
                img,
                new LinearLayout.LayoutParams(
                        -1,
                        220
                )
        );

        TextView logo =
                text(
                        "DATRIDHA",
                        30,
                        BROWN,
                        true
                );

        logo.setGravity(
                Gravity.CENTER
        );

        root.addView(logo);

        root.addView(
                text(
                        "دقلة النور من بشني",
                        18,
                        GREEN,
                        true
                )
        );

        Button phone =
                bigButton(
                        "☎  51 022 448",
                        GREEN
                );

        root.addView(
                phone,
                new LinearLayout.LayoutParams(
                        -1,
                        56
                )
        );

        phone.setOnClickListener(
                v -> callPhone()
        );

        Button email =
                bigButton(
                        "✉  ridhatouil1992@gmail.com",
                        BROWN
                );

        root.addView(
                email,
                new LinearLayout.LayoutParams(
                        -1,
                        56
                )
        );

        email.setOnClickListener(
                v -> sendEmail()
        );

        Button settings =
                bigButton(
                        "⚙  "
                                + tr(
                                "الإعدادات",
                                "Paramètres",
                                "Settings"
                        ),
                        GREEN
                );

        root.addView(
                settings,
                new LinearLayout.LayoutParams(
                        -1,
                        56
                )
        );

        settings.setOnClickListener(
                v -> showSettings()
        );

        addBottomNavigation(4);
    }

    // =========================================================
    // SETTINGS
    // =========================================================

    void showSettings() {

        currentPage = "settings";

        baseScreen();

        addTopBar(
                tr(
                        "الإعدادات",
                        "Paramètres",
                        "Settings"
                ),
                false
        );

        setting(
                "◉",
                tr(
                        "اللغة",
                        "Langue",
                        "Language"
                ),
                "العربية / Français / English"
        );

        setting(
                "♢",
                tr(
                        "الإشعارات",
                        "Notifications",
                        "Notifications"
                ),
                tr(
                        "تفعيل الإشعارات",
                        "Activer les notifications",
                        "Enable notifications"
                )
        );

        setting(
                "ⓘ",
                "DATRIDHA",
                tr(
                        "حول تطبيق DATRIDHA",
                        "À propos de DATRIDHA",
                        "About DATRIDHA"
                )
        );

        setting(
                "▣",
                tr(
                        "الخصوصية",
                        "Confidentialité",
                        "Privacy"
                ),
                tr(
                        "سياسة الخصوصية",
                        "Politique de confidentialité",
                        "Privacy policy"
                )
        );

        setting(
                "?",
                tr(
                        "المساعدة",
                        "Aide",
                        "Help"
                ),
                tr(
                        "نحن هنا لمساعدتك",
                        "Nous sommes là pour vous aider",
                        "We are here to help"
                )
        );

        Button change =
                bigButton(
                        tr(
                                "تغيير اللغة",
                                "Changer la langue",
                                "Change language"
                        ),
                        GREEN
                );

        root.addView(
                change,
                new LinearLayout.LayoutParams(
                        -1,
                        56
                )
        );

        change.setOnClickListener(
                v -> {

                    if("ar".equals(language)) {
                        language = "fr";
                    } else if("fr".equals(language)) {
                        language = "en";
                    } else {
                        language = "ar";
                    }

                    showSettings();
                }
        );
    }

    void setting(
            String icon,
            String title,
            String value) {

        LinearLayout c = card();

        LinearLayout row =
                new LinearLayout(this);

        row.setGravity(
                Gravity.CENTER_VERTICAL
        );

        TextView i =
                text(
                        icon,
                        24,
                        GREEN,
                        true
                );

        i.setGravity(Gravity.CENTER);

        row.addView(
                i,
                new LinearLayout.LayoutParams(
                        45,
                        55
                )
        );

        LinearLayout data =
                new LinearLayout(this);

        data.setOrientation(
                LinearLayout.VERTICAL
        );

        data.addView(
                text(
                        title,
                        16,
                        BROWN,
                        true
                )
        );

        data.addView(
                text(
                        value,
                        12,
                        GRAY,
                        false
                )
        );

        row.addView(
                data,
                new LinearLayout.LayoutParams(
                        0,
                        -2,
                        1
                )
        );

        c.addView(row);

        root.addView(c);
    }

    // =========================================================
    // TOP BAR
    // =========================================================

    void addTopBar(
            String title,
            boolean menu) {

        LinearLayout bar =
                new LinearLayout(this);

        bar.setGravity(
                Gravity.CENTER_VERTICAL
        );

        bar.setPadding(
                5,
                3,
                5,
                3
        );

        if(menu) {

            Button m =
                    smallButton(
                            "☰",
                            GREEN
                    );

            bar.addView(
                    m,
                    new LinearLayout.LayoutParams(
                            50,
                            50
                    )
            );

            m.setOnClickListener(
                    v -> showSettings()
            );
        } else {

            Button back =
                    smallButton(
                            "‹",
                            GREEN
                    );

            bar.addView(
                    back,
                    new LinearLayout.LayoutParams(
                            50,
                            50
                    )
            );

            back.setOnClickListener(
                    v -> onBackPressed()
            );
        }

        TextView t =
                text(
                        title,
                        20,
                        GREEN,
                        true
                );

        t.setGravity(
                Gravity.CENTER
        );

        bar.addView(
                t,
                new LinearLayout.LayoutParams(
                        0,
                        55,
                        1
                )
        );

        TextView logo =
                text(
                        "DATRIDHA",
                        15,
                        BROWN,
                        true
                );

        logo.setGravity(
                Gravity.CENTER
        );

        bar.addView(
                logo,
                new LinearLayout.LayoutParams(
                        90,
                        55
                )
        );

        root.addView(bar);
    }

    // =========================================================
    // BOTTOM NAV
    // =========================================================

    void addBottomNavigation(int selected) {

        LinearLayout nav =
                new LinearLayout(this);

        nav.setGravity(
                Gravity.CENTER
        );

        nav.setPadding(
                2,
                4,
                2,
                4
        );

        String[] labels = {
                tr("⌂\nالرئيسية","⌂\nAccueil","⌂\nHome"),
                tr("♢\nالعروض","♢\nOffres","♢\nOffers"),
                tr("▣\nالطلبات","▣\nCommandes","▣\nOrders"),
                tr("☎\nاتصال","☎\nContact","☎\nContact")
        };

        for(int i = 0; i < labels.length; i++) {

            Button b =
                    new Button(this);

            b.setText(labels[i]);
            b.setTextSize(10);
            b.setAllCaps(false);

            if(i == selected) {
                b.setTextColor(GREEN);
            } else {
                b.setTextColor(BROWN);
            }

            b.setBackgroundColor(
                    Color.TRANSPARENT
            );

            final int index = i;

            b.setOnClickListener(
                    v -> {

                        if(index == 0) {
                            showHome();
                        } else if(index == 1) {
                            showBuy();
                        } else if(index == 2) {
                            showOrders();
                        } else {
                            showContact();
                        }
                    }
            );

            nav.addView(
                    b,
                    new LinearLayout.LayoutParams(
                            0,
                            58,
                            1
                    )
            );
        }

        root.addView(nav);
    }

    // =========================================================
    // BASE SCREEN
    // =========================================================

    void baseScreen() {

        ScrollView scroll =
                new ScrollView(this);

        scroll.setFillViewport(true);

        root =
                new LinearLayout(this);

        root.setOrientation(
                LinearLayout.VERTICAL
        );

        root.setPadding(
                10,
                5,
                10,
                5
        );

        root.setBackgroundColor(
                CREAM
        );

        scroll.addView(root);

        setContentView(scroll);
    }

    // =========================================================
    // IMAGE
    // =========================================================

    ImageView image(
            int resource,
            ImageView.ScaleType type) {

        ImageView v =
                new ImageView(this);

        v.setImageResource(resource);
        v.setScaleType(type);

        return v;
    }

    // =========================================================
    // TEXT
    // =========================================================

    TextView text(
            String value,
            int size,
            int color,
            boolean bold) {

        TextView t =
                new TextView(this);

        t.setText(value);
        t.setTextSize(size);
        t.setTextColor(color);

        t.setPadding(
                5,
                4,
                5,
                4
        );

        if(bold) {

            t.setTypeface(
                    Typeface.DEFAULT,
                    Typeface.BOLD
            );
        }

        return t;
    }

    // =========================================================
    // BIG BUTTON
    // =========================================================

    Button bigButton(
            String title,
            int color) {

        Button b =
                new Button(this);

        b.setText(title);
        b.setTextSize(15);
        b.setAllCaps(false);

        if(color == IVORY) {
            b.setTextColor(GREEN);
        } else {
            b.setTextColor(WHITE);
        }

        GradientDrawable bg =
                new GradientDrawable();

        bg.setColor(color);
        bg.setCornerRadius(25);

        b.setBackground(bg);

        b.setPadding(
                8,
                3,
                8,
                3
        );

        return b;
    }

    // =========================================================
    // SMALL BUTTON
    // =========================================================

    Button smallButton(
            String title,
            int color) {

        Button b =
                new Button(this);

        b.setText(title);
        b.setTextSize(13);
        b.setAllCaps(false);

        if(color == WHITE) {
            b.setTextColor(BROWN);
        } else {
            b.setTextColor(WHITE);
        }

        GradientDrawable bg =
                new GradientDrawable();

        bg.setColor(color);
        bg.setCornerRadius(22);

        b.setBackground(bg);

        return b;
    }

    // =========================================================
    // CARD
    // =========================================================

    LinearLayout card() {

        LinearLayout c =
                new LinearLayout(this);

        c.setOrientation(
                LinearLayout.VERTICAL
        );

        c.setPadding(
                12,
                12,
                12,
                12
        );

        GradientDrawable bg =
                new GradientDrawable();

        bg.setColor(WHITE);
        bg.setCornerRadius(20);
        bg.setStroke(
                1,
                Color.rgb(232,224,212)
        );

        c.setBackground(bg);

        LinearLayout.LayoutParams lp =
                new LinearLayout.LayoutParams(
                        -1,
                        -2
                );

        lp.setMargins(
                0,
                6,
                0,
                6
        );

        c.setLayoutParams(lp);

        return c;
    }

    // =========================================================
    // IMAGE PICKER
    // =========================================================

    void chooseImage() {

        Intent intent =
                new Intent(
                        Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                );

        startActivityForResult(
                intent,
                100
        );
    }

    @Override
    protected void onActivityResult(
            int requestCode,
            int resultCode,
            Intent data) {

        super.onActivityResult(
                requestCode,
                resultCode,
                data
        );

        if(
                requestCode == 100
                        && resultCode == RESULT_OK
        ) {

            Toast.makeText(
                    this,
                    tr(
                            "تم اختيار الصورة",
                            "Image sélectionnée",
                            "Image selected"
                    ),
                    Toast.LENGTH_SHORT
            ).show();
        }
    }

    // =========================================================
    // PHONE
    // =========================================================

    void callPhone() {

        try {

            Intent i =
                    new Intent(
                            Intent.ACTION_DIAL,
                            Uri.parse(
                                    "tel:51022448"
                            )
                    );

            startActivity(i);

        } catch(Exception e) {

            Toast.makeText(
                    this,
                    "51022448",
                    Toast.LENGTH_SHORT
            ).show();
        }
    }

    // =========================================================
    // EMAIL
    // =========================================================

    void sendEmail() {

        try {

            Intent i =
                    new Intent(
                            Intent.ACTION_SENDTO
                    );

            i.setData(
                    Uri.parse(
                            "mailto:ridhatouil1992@gmail.com"
                    )
            );

            i.putExtra(
                    Intent.EXTRA_SUBJECT,
                    "DATRIDHA"
            );

            startActivity(i);

        } catch(Exception e) {

            Toast.makeText(
                    this,
                    "ridhatouil1992@gmail.com",
                    Toast.LENGTH_SHORT
            ).show();
        }
    }

    // =========================================================
    // BACK NAVIGATION
    // =========================================================

    @Override
    public void onBackPressed() {

        if("details".equals(currentPage)) {

            showBuy();

        } else if(
                "buy".equals(currentPage)
                        || "sell".equals(currentPage)
                        || "orders".equals(currentPage)
                        || "contact".equals(currentPage)
                        || "settings".equals(currentPage)
        ) {

            showHome();

        } else if("home".equals(currentPage)) {

            showSplash();

        } else if("splash".equals(currentPage)) {

            finish();

        } else {

            finish();
        }
    }
}
