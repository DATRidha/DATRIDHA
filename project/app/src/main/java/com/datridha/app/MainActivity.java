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

    LinearLayout root;
    LinearLayout content;

    boolean french = false;

    ArrayList<String> orders = new ArrayList<>();

    // =========================================================
    // DATRIDHA COLORS
    // =========================================================

    final int GREEN = Color.rgb(31, 112, 61);
    final int DARK_GREEN = Color.rgb(14, 76, 40);
    final int DEEP_GREEN = Color.rgb(7, 55, 29);

    final int GOLD = Color.rgb(211, 166, 64);
    final int LIGHT_GOLD = Color.rgb(239, 216, 155);

    final int BROWN = Color.rgb(111, 70, 35);
    final int DARK_BROWN = Color.rgb(67, 40, 20);

    final int ORANGE = Color.rgb(211, 105, 40);

    final int CREAM = Color.rgb(250, 246, 235);
    final int BEIGE = Color.rgb(242, 231, 204);

    final int TEXT = Color.rgb(62, 55, 45);
    final int MUTED = Color.rgb(120, 110, 95);

    final int WHITE = Color.WHITE;

    // =========================================================
    // CREATE ACTIVITY
    // =========================================================

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        showHome();
    }

    // =========================================================
    // BASIC HELPERS
    // =========================================================

    TextView text(
            String value,
            float size,
            int color,
            boolean bold
    ) {

        TextView t = new TextView(this);

        t.setText(value);
        t.setTextSize(size);
        t.setTextColor(color);

        t.setTypeface(
                bold
                        ? Typeface.create("sans-serif", Typeface.BOLD)
                        : Typeface.create("sans-serif", Typeface.NORMAL)
        );

        t.setGravity(Gravity.CENTER_VERTICAL);

        return t;
    }

    GradientDrawable background(
            int color,
            float radius
    ) {

        GradientDrawable g = new GradientDrawable();

        g.setColor(color);
        g.setCornerRadius(radius);

        return g;
    }

    GradientDrawable border(
            int color,
            int strokeColor,
            int strokeWidth,
            float radius
    ) {

        GradientDrawable g = new GradientDrawable();

        g.setColor(color);
        g.setCornerRadius(radius);
        g.setStroke(strokeWidth, strokeColor);

        return g;
    }

    GradientDrawable gradient(
            int first,
            int second,
            int third
    ) {

        return new GradientDrawable(
                GradientDrawable.Orientation.TL_BR,
                new int[]{
                        first,
                        second,
                        third
                }
        );
    }

    LinearLayout vertical() {

        LinearLayout l = new LinearLayout(this);

        l.setOrientation(
                LinearLayout.VERTICAL
        );

        return l;
    }

    LinearLayout horizontal() {

        LinearLayout l = new LinearLayout(this);

        l.setOrientation(
                LinearLayout.HORIZONTAL
        );

        l.setGravity(
                Gravity.CENTER_VERTICAL
        );

        return l;
    }

    void gap(
            LinearLayout parent,
            int height
    ) {

        Space s = new Space(this);

        parent.addView(
                s,
                new LinearLayout.LayoutParams(
                        1,
                        height
                )
        );
    }

    Button actionButton(
            String title,
            int color
    ) {

        Button b = new Button(this);

        b.setText(title);
        b.setTextColor(WHITE);
        b.setTextSize(13);

        b.setTypeface(
                Typeface.create(
                        "sans-serif",
                        Typeface.BOLD
                )
        );

        b.setAllCaps(false);

        b.setGravity(
                Gravity.CENTER
        );

        b.setPadding(
                3,
                0,
                3,
                0
        );

        b.setMinHeight(0);
        b.setMinimumHeight(0);

        b.setBackground(
                background(
                        color,
                        45
                )
        );

        return b;
    }

    EditText input(
            String hint
    ) {

        EditText e = new EditText(this);

        e.setHint(hint);

        e.setHintTextColor(
                MUTED
        );

        e.setTextColor(
                TEXT
        );

        e.setTextSize(15);

        e.setSingleLine(true);

        e.setPadding(
                18,
                0,
                18,
                0
        );

        e.setBackground(
                border(
                        WHITE,
                        Color.rgb(
                                220,
                                207,
                                181
                        ),
                        1,
                        25
                )
        );

        return e;
    }

    LinearLayout card() {

        LinearLayout c = vertical();

        c.setPadding(
                18,
                17,
                18,
                17
        );

        c.setBackground(
                border(
                        Color.rgb(
                                255,
                                253,
                                247
                        ),
                        Color.rgb(
                                228,
                                214,
                                184
                        ),
                        1,
                        25
                )
        );

        return c;
    }

    // =========================================================
    // HOME SCREEN
    // =========================================================

    void showHome() {

        root = vertical();

        root.setBackground(
                background(
                        CREAM,
                        0
                )
        );

        // -----------------------------------------------------
        // TOP BAR
        // -----------------------------------------------------

        LinearLayout top = horizontal();

        top.setPadding(
                14,
                10,
                14,
                8
        );

        top.setBackground(
                background(
                        WHITE,
                        0
                )
        );

        // Logo
        LinearLayout logoBox = horizontal();

        TextView palm = text(
                "🌴",
                26,
                GREEN,
                false
        );

        logoBox.addView(
                palm,
                new LinearLayout.LayoutParams(
                        35,
                        52
                )
        );

        TextView logo = text(
                "DATRIDHA",
                23,
                DARK_GREEN,
                true
        );

        logoBox.addView(
                logo,
                new LinearLayout.LayoutParams(
                        0,
                        52,
                        1
                )
        );

        top.addView(
                logoBox,
                new LinearLayout.LayoutParams(
                        0,
                        58,
                        1
                )
        );

        Button language = actionButton(
                french
                        ? "عربي"
                        : "FR",
                GREEN
        );

        top.addView(
                language,
                new LinearLayout.LayoutParams(
                        62,
                        45
                )
        );

        language.setOnClickListener(
                v -> {

                    french = !french;

                    showHome();
                }
        );

        root.addView(top);

        // -----------------------------------------------------
        // HERO BRAND AREA
        // -----------------------------------------------------

        LinearLayout hero = vertical();

        hero.setGravity(
                Gravity.CENTER
        );

        hero.setPadding(
                15,
                18,
                15,
                18
        );

        hero.setBackground(
                gradient(
                        Color.rgb(
                                255,
                                250,
                                235
                        ),
                        Color.rgb(
                                245,
                                230,
                                187
                        ),
                        Color.rgb(
                                222,
                                190,
                                107
                        )
                )
        );

        TextView palmBig = text(
                "🌴",
                62,
                GREEN,
                false
        );

        palmBig.setGravity(
                Gravity.CENTER
        );

        hero.addView(palmBig);

        TextView brand = text(
                "DATRIDHA",
                39,
                DARK_BROWN,
                true
        );

        brand.setGravity(
                Gravity.CENTER
        );

        hero.addView(brand);

        TextView brandLine = text(
                french
                        ? "DEGLET NOUR • BECHNI"
                        : "دَقْلَةُ النُّور • بَشْنِي",
                16,
                GREEN,
                true
        );

        brandLine.setGravity(
                Gravity.CENTER
        );

        hero.addView(brandLine);

        gap(
                hero,
                4
        );

        TextView slogan = text(
                french
                        ? "Plus qu'une application… une opportunité !"
                        : "أكثر من تطبيق… فرصة تجارية !",
                13,
                BROWN,
                true
        );

        slogan.setGravity(
                Gravity.CENTER
        );

        hero.addView(slogan);

        root.addView(
                hero,
                new LinearLayout.LayoutParams(
                        -1,
                        235
                )
        );

        // -----------------------------------------------------
        // MAIN SCROLL
        // -----------------------------------------------------

        ScrollView scroll =
                new ScrollView(this);

        content = vertical();

        content.setPadding(
                15,
                16,
                15,
                18
        );

        scroll.addView(content);

        root.addView(
                scroll,
                new LinearLayout.LayoutParams(
                        0,
                        0,
                        1
                )
        );

        showHomeContent();

        // -----------------------------------------------------
        // BOTTOM NAVIGATION
        // -----------------------------------------------------

        createBottomNavigation();

        setContentView(root);
    }

    // =========================================================
    // HOME CONTENT
    // =========================================================

    void showHomeContent() {

        clearContent();

        TextView welcome = text(
                french
                        ? "Bienvenue sur DATRIDHA"
                        : "مرحبًا بك في DATRIDHA",
                23,
                DARK_BROWN,
                true
        );

        welcome.setGravity(
                Gravity.CENTER
        );

        content.addView(welcome);

        TextView intro = text(
                french
                        ? "La plateforme dédiée au commerce de gros de Deglet Nour."
                        : "منصة متخصصة في تجارة الجملة لدقلة النور.",
                14,
                TEXT,
                false
        );

        intro.setGravity(
                Gravity.CENTER
        );

        content.addView(intro);

        gap(
                content,
                17
        );

        // -----------------------------------------------------
        // BUY
        // -----------------------------------------------------

        LinearLayout buyCard = card();

        TextView buyTitle = text(
                "🛒  " +
                        (
                                french
                                        ? "Acheter Deglet Nour"
                                        : "شراء دقلة النور"
                        ),
                20,
                GREEN,
                true
        );

        buyCard.addView(buyTitle);

        gap(
                buyCard,
                4
        );

        buyCard.addView(
                text(
                        french
                                ? "Trouvez des offres de producteurs et grossistes."
                                : "اكتشف عروض المنتجين وتجار الجملة.",
                        14,
                        TEXT,
                        false
                )
        );

        gap(
                buyCard,
                10
        );

        Button buyButton = actionButton(
                french
                        ? "Voir les offres"
                        : "مشاهدة العروض",
                GREEN
        );

        buyButton.setOnClickListener(
                v -> showBuy()
        );

        buyCard.addView(
                buyButton,
                new LinearLayout.LayoutParams(
                        -1,
                        52
                )
        );

        content.addView(buyCard);

        gap(
                content,
                12
        );

        // -----------------------------------------------------
        // SELL
        // -----------------------------------------------------

        LinearLayout sellCard = card();

        TextView sellTitle = text(
                "📦  " +
                        (
                                french
                                        ? "Vendre Deglet Nour"
                                        : "بيع دقلة النور"
                        ),
                20,
                ORANGE,
                true
        );

        sellCard.addView(sellTitle);

        gap(
                sellCard,
                4
        );

        sellCard.addView(
                text(
                        french
                                ? "Publiez votre quantité, prix et photos."
                                : "اعرض الكمية والسعر والصور الخاصة بك.",
                        14,
                        TEXT,
                        false
                )
        );

        gap(
                sellCard,
                10
        );

        Button sellButton = actionButton(
                french
                        ? "Publier une offre"
                        : "إضافة عرض بيع",
                ORANGE
        );

        sellButton.setOnClickListener(
                v -> showSell()
        );

        sellCard.addView(
                sellButton,
                new LinearLayout.LayoutParams(
                        -1,
                        52
                )
        );

        content.addView(sellCard);

        gap(
                content,
                12
        );

        // -----------------------------------------------------
        // WHY DATRIDHA
        // -----------------------------------------------------

        LinearLayout why = card();

        TextView whyTitle = text(
                french
                        ? "Pourquoi DATRIDHA ?"
                        : "لماذا DATRIDHA؟",
                19,
                BROWN,
                true
        );

        why.addView(whyTitle);

        gap(
                why,
                5
        );

        addFeature(
                why,
                french
                        ? "Commerce direct"
                        : "تجارة مباشرة"
        );

        addFeature(
                why,
                french
                        ? "Deglet Nour de qualité"
                        : "دقلة نور ذات جودة"
        );

        addFeature(
                why,
                french
                        ? "Producteurs de Bechni"
                        : "منتجو بشني"
        );

        addFeature(
                why,
                french
                        ? "Contact rapide"
                        : "تواصل سريع"
        );

        content.addView(why);

        gap(
                content,
                18
        );

        TextView footer = text(
                french
                        ? "🌴 DATRIDHA"
                        : "🌴 DATRIDHA",
                18,
                GREEN,
                true
        );

        footer.setGravity(
                Gravity.CENTER
        );

        content.addView(footer);

        TextView footer2 = text(
                french
                        ? "Plus qu'une application… une opportunité !"
                        : "أكثر من تطبيق… فرصة تجارية !",
                12,
                MUTED,
                false
        );

        footer2.setGravity(
                Gravity.CENTER
        );

        content.addView(footer2);
    }

    void addFeature(
            LinearLayout parent,
            String value
    ) {

        TextView t = text(
                "✓  " + value,
                15,
                TEXT,
                false
        );

        t.setPadding(
                0,
                5,
                0,
                5
        );

        parent.addView(t);
    }

    // =========================================================
    // BOTTOM NAVIGATION
    // =========================================================

    void createBottomNavigation() {

        LinearLayout nav = horizontal();

        nav.setPadding(
                5,
                6,
                5,
                7
        );

        nav.setBackground(
                background(
                        WHITE,
                        0
                )
        );

        Button contact = actionButton(
                french
                        ? "☎\nContact"
                        : "☎\nتواصل",
                DARK_GREEN
        );

        Button ordersButton = actionButton(
                french
                        ? "📋\nCommandes"
                        : "📋\nالطلبات",
                BROWN
        );

        Button sell = actionButton(
                french
                        ? "📦\nVendre"
                        : "📦\nبيع",
                ORANGE
        );

        Button buy = actionButton(
                french
                        ? "🛒\nAcheter"
                        : "🛒\nشراء",
                GREEN
        );

        nav.addView(
                contact,
                new LinearLayout.LayoutParams(
                        0,
                        64,
                        1
                )
        );

        nav.addView(
                ordersButton,
                new LinearLayout.LayoutParams(
                        0,
                        64,
                        1
                )
        );

        nav.addView(
                sell,
                new LinearLayout.LayoutParams(
                        0,
                        64,
                        1
                )
        );

        nav.addView(
                buy,
                new LinearLayout.LayoutParams(
                        0,
                        64,
                        1
                )
        );

        contact.setOnClickListener(
                v -> showContact()
        );

        ordersButton.setOnClickListener(
                v -> showOrders()
        );

        sell.setOnClickListener(
                v -> showSell()
        );

        buy.setOnClickListener(
                v -> showBuy()
        );

        root.addView(nav);
    }

    // =========================================================
    // BUY SCREEN
    // =========================================================

    void showBuy() {

        clearContent();

        addBack();

        TextView title = text(
                french
                        ? "Acheter Deglet Nour"
                        : "شراء دقلة النور",
                26,
                GREEN,
                true
        );

        title.setGravity(
                Gravity.CENTER
        );

        content.addView(title);

        gap(
                content,
                7
        );

        TextView info = text(
                french
                        ? "Offres disponibles en gros."
                        : "العروض المتوفرة بالجملة.",
                14,
                TEXT,
                false
        );

        info.setGravity(
                Gravity.CENTER
        );

        content.addView(info);

        gap(
                content,
                15
        );

        addOffer(
                "🌴 Deglet Nour — Bechni",
                french
                        ? "Origine : Bechni"
                        : "المصدر: بشني",
                french
                        ? "Quantité : selon disponibilité"
                        : "الكمية: حسب التوفر"
        );

        gap(
                content,
                12
        );

        addOffer(
                "🌴 Deglet Nour Premium",
                french
                        ? "Qualité supérieure"
                        : "جودة ممتازة",
                french
                        ? "Prix : à négocier"
                        : "السعر: قابل للتفاوض"
        );
    }

    void addOffer(
            String title,
            String line1,
            String line2
    ) {

        LinearLayout offer = card();

        offer.addView(
                text(
                        title,
                        19,
                        BROWN,
                        true
                )
        );

        offer.addView(
                text(
                        line1,
                        14,
                        TEXT,
                        false
                )
        );

        offer.addView(
                text(
                        line2,
                        14,
                        TEXT,
                        false
                )
        );

        gap(
                offer,
                10
        );

        Button request = actionButton(
                french
                        ? "Demander cette offre"
                        : "طلب هذا العرض",
                GREEN
        );

        request.setOnClickListener(
                v -> {

                    orders.add(
                            french
                                    ? "Demande Deglet Nour"
                                    : "طلب دقلة النور"
                    );

                    Toast.makeText(
                            this,
                            french
                                    ? "Demande enregistrée"
                                    : "تم تسجيل الطلب",
                            Toast.LENGTH_SHORT
                    ).show();

                    showOrders();
                }
        );

        offer.addView(
                request,
                new LinearLayout.LayoutParams(
                        -1,
                        52
                )
        );

        content.addView(offer);
    }

    // =========================================================
    // SELL SCREEN
    // =========================================================

    void showSell() {

        clearContent();

        addBack();

        TextView title = text(
                french
                        ? "Vendre Deglet Nour"
                        : "بيع دقلة النور",
                26,
                ORANGE,
                true
        );

        title.setGravity(
                Gravity.CENTER
        );

        content.addView(title);

        gap(
                content,
                7
        );

        TextView info = text(
                french
                        ? "Publiez votre offre pour les acheteurs."
                        : "انشر عرضك ليصل إلى المشترين.",
                14,
                TEXT,
                false
        );

        info.setGravity(
                Gravity.CENTER
        );

        content.addView(info);

        gap(
                content,
                15
        );

        EditText seller = input(
                french
                        ? "Nom du vendeur *"
                        : "اسم البائع *"
        );

        content.addView(
                seller,
                new LinearLayout.LayoutParams(
                        -1,
                        58
                )
        );

        gap(
                content,
                8
        );

        EditText quantity = input(
                french
                        ? "Quantité (kg / tonnes) *"
                        : "الكمية (كغ / طن) *"
        );

        content.addView(
                quantity,
                new LinearLayout.LayoutParams(
                        -1,
                        58
                )
        );

        gap(
                content,
                8
        );

        EditText price = input(
                french
                        ? "Prix proposé"
                        : "السعر المقترح"
        );

        content.addView(
                price,
                new LinearLayout.LayoutParams(
                        -1,
                        58
                )
        );

        gap(
                content,
                8
        );

        EditText phone = input(
                french
                        ? "Téléphone *"
                        : "رقم الهاتف *"
        );

        content.addView(
                phone,
                new LinearLayout.LayoutParams(
                        -1,
                        58
                )
        );

        gap(
                content,
                8
        );

        EditText description = input(
                french
                        ? "Qualité / emballage / détails"
                        : "الجودة / التعبئة / التفاصيل"
        );

        description.setSingleLine(false);

        description.setGravity(
                Gravity.TOP
        );

        description.setPadding(
                18,
                15,
                18,
                15
        );

        content.addView(
                description,
                new LinearLayout.LayoutParams(
                        -1,
                        110
                )
        );

        gap(
                content,
                12
        );

        Button photos = actionButton(
                french
                        ? "📷  Ajouter des photos"
                        : "📷  إضافة صور",
                BROWN
        );

        photos.setOnClickListener(
                v -> chooseImages()
        );

        content.addView(
                photos,
                new LinearLayout.LayoutParams(
                        -1,
                        54
                )
        );

        gap(
                content,
                13
        );

        Button publish = actionButton(
                french
                        ? "
