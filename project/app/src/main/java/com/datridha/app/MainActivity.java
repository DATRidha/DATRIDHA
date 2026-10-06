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

    // DATRIDHA BRAND COLORS
    final int GREEN = Color.rgb(23, 115, 58);
    final int DARK_GREEN = Color.rgb(12, 76, 39);
    final int GOLD = Color.rgb(212, 164, 55);
    final int BROWN = Color.rgb(91, 58, 31);
    final int DARK_BROWN = Color.rgb(55, 35, 18);
    final int ORANGE = Color.rgb(204, 103, 35);
    final int CREAM = Color.rgb(250, 246, 235);
    final int LIGHT_GREEN = Color.rgb(232, 244, 232);
    final int TEXT = Color.rgb(55, 50, 43);
    final int WHITE = Color.WHITE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showHome();
    }

    // =========================================================
    // BASIC UI HELPERS
    // =========================================================

    TextView text(String value, float size, int color, boolean bold) {
        TextView t = new TextView(this);
        t.setText(value);
        t.setTextSize(size);
        t.setTextColor(color);
        t.setTypeface(bold ? Typeface.DEFAULT_BOLD : Typeface.DEFAULT);
        t.setGravity(Gravity.CENTER_VERTICAL);
        t.setPadding(6, 5, 6, 5);
        return t;
    }

    GradientDrawable bg(int color, float radius) {
        GradientDrawable g = new GradientDrawable();
        g.setColor(color);
        g.setCornerRadius(radius);
        return g;
    }

    GradientDrawable strokeBg(
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

    GradientDrawable brandGradient() {
        GradientDrawable g = new GradientDrawable(
                GradientDrawable.Orientation.TL_BR,
                new int[]{
                        Color.rgb(255, 250, 236),
                        Color.rgb(245, 231, 190),
                        Color.rgb(226, 196, 119)
                }
        );
        return g;
    }

    Button button(String title, int color) {
        Button b = new Button(this);
        b.setText(title);
        b.setTextColor(WHITE);
        b.setTextSize(14);
        b.setTypeface(Typeface.DEFAULT_BOLD);
        b.setAllCaps(false);
        b.setGravity(Gravity.CENTER);
        b.setPadding(8, 2, 8, 2);
        b.setBackground(bg(color, 50));
        return b;
    }

    EditText input(String hint) {
        EditText e = new EditText(this);
        e.setHint(hint);
        e.setTextSize(15);
        e.setTextColor(TEXT);
        e.setHintTextColor(Color.rgb(135, 125, 110));
        e.setPadding(18, 10, 18, 10);
        e.setBackground(strokeBg(
                WHITE,
                Color.rgb(220, 210, 190),
                1,
                28
        ));
        return e;
    }

    LinearLayout vertical() {
        LinearLayout l = new LinearLayout(this);
        l.setOrientation(LinearLayout.VERTICAL);
        return l;
    }

    LinearLayout horizontal() {
        LinearLayout l = new LinearLayout(this);
        l.setOrientation(LinearLayout.HORIZONTAL);
        l.setGravity(Gravity.CENTER_VERTICAL);
        return l;
    }

    void space(LinearLayout parent, int height) {
        Space s = new Space(this);
        parent.addView(
                s,
                new LinearLayout.LayoutParams(1, height)
        );
    }

    LinearLayout card() {
        LinearLayout c = vertical();
        c.setPadding(18, 16, 18, 16);
        c.setBackground(
                strokeBg(
                        Color.argb(245, 255, 252, 244),
                        Color.rgb(231, 218, 189),
                        1,
                        30
                )
        );
        return c;
    }

    void clearContent() {
        content.removeAllViews();
    }

    // =========================================================
    // HOME
    // =========================================================

    void showHome() {

        root = vertical();

        // FIXED: setBackground needs Drawable
        root.setBackground(bg(CREAM, 0));

        // TOP BAR
        LinearLayout top = horizontal();
        top.setPadding(14, 10, 14, 6);

        // FIXED: setBackground needs Drawable
        top.setBackground(bg(WHITE, 0));

        TextView miniLogo = text(
                "🌴 DATRIDHA",
                23,
                DARK_GREEN,
                true
        );

        top.addView(
                miniLogo,
                new LinearLayout.LayoutParams(0, 55, 1)
        );

        Button language = button(
                french ? "عربي" : "FR",
                GREEN
        );

        language.setTextSize(12);

        language.setOnClickListener(v -> {
            french = !french;
            showHome();
        });

        top.addView(
                language,
                new LinearLayout.LayoutParams(65, 46)
        );

        root.addView(top);

        // BRAND HERO
        LinearLayout hero = vertical();
        hero.setGravity(Gravity.CENTER);
        hero.setPadding(18, 18, 18, 18);
        hero.setBackground(brandGradient());

        TextView palm = text(
                "🌴",
                52,
                GREEN,
                false
        );

        palm.setGravity(Gravity.CENTER);
        hero.addView(palm);

        TextView brand = text(
                "DATRIDHA",
                40,
                DARK_BROWN,
                true
        );

        brand.setGravity(Gravity.CENTER);
        hero.addView(brand);

        TextView line = text(
                french
                        ? "Deglet Nour de Bechni"
                        : "دقلة النور من بشني",
                19,
                GREEN,
                true
        );

        line.setGravity(Gravity.CENTER);
        hero.addView(line);

        TextView slogan = text(
                french
                        ? "Achetez • Vendez • Échangez"
                        : "اشترِ • بِع • تعامل",
                14,
                BROWN,
                true
        );

        slogan.setGravity(Gravity.CENTER);
        hero.addView(slogan);

        root.addView(
                hero,
                new LinearLayout.LayoutParams(-1, 215)
        );

        // SCROLL CONTENT
        ScrollView scroll = new ScrollView(this);

        content = vertical();
        content.setPadding(16, 16, 16, 20);

        scroll.addView(content);

        root.addView(
                scroll,
                new LinearLayout.LayoutParams(0, 0, 1)
        );

        // BOTTOM NAVIGATION
        LinearLayout nav = horizontal();
        nav.setPadding(6, 7, 6, 7);

        // FIXED: setBackground needs Drawable
        nav.setBackground(bg(WHITE, 0));

        Button buy = button(
                french ? "🛒\nAcheter" : "🛒\nشراء",
                GREEN
        );

        Button sell = button(
                french ? "📦\nVendre" : "📦\nبيع",
                ORANGE
        );

        Button ordersButton = button(
                french ? "📋\nCommandes" : "📋\nالطلبات",
                BROWN
        );

        Button contact = button(
                french ? "☎\nContact" : "☎\nتواصل",
                DARK_GREEN
        );

        nav.addView(
                buy,
                new LinearLayout.LayoutParams(0, 58, 1)
        );

        nav.addView(
                sell,
                new LinearLayout.LayoutParams(0, 58, 1)
        );

        nav.addView(
                ordersButton,
                new LinearLayout.LayoutParams(0, 58, 1)
        );

        nav.addView(
                contact,
                new LinearLayout.LayoutParams(0, 58, 1)
        );

        buy.setOnClickListener(v -> showBuy());
        sell.setOnClickListener(v -> showSell());
        ordersButton.setOnClickListener(v -> showOrders());
        contact.setOnClickListener(v -> showContact());

        root.addView(nav);

        showHomeContent();

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
                24,
                DARK_BROWN,
                true
        );

        welcome.setGravity(Gravity.CENTER);
        content.addView(welcome);

        TextView description = text(
                french
                        ? "La plateforme de commerce de gros de Deglet Nour."
                        : "منصة تجارة الجملة لدقلة النور.",
                14,
                TEXT,
                false
        );

        description.setGravity(Gravity.CENTER);
        content.addView(description);

        space(content, 16);

        // BUY CARD
        LinearLayout buyCard = card();

        TextView buyTitle = text(
                "🛒  " +
                        (french
                                ? "Acheter Deglet Nour"
                                : "شراء دقلة النور"),
                20,
                GREEN,
                true
        );

        buyCard.addView(buyTitle);

        buyCard.addView(text(
                french
                        ? "Découvrez les offres disponibles."
                        : "اكتشف عروض دقلة النور المتوفرة.",
                14,
                TEXT,
                false
        ));

        space(buyCard, 8);

        Button buy = button(
                french ? "Voir les offres" : "مشاهدة العروض",
                GREEN
        );

        buy.setOnClickListener(v -> showBuy());

        buyCard.addView(
                buy,
                new LinearLayout.LayoutParams(-1, 50)
        );

        content.addView(buyCard);

        space(content, 12);

        // SELL CARD
        LinearLayout sellCard = card();

        TextView sellTitle = text(
                "📦  " +
                        (french
                                ? "Vendre Deglet Nour"
                                : "بيع دقلة النور"),
                20,
                ORANGE,
                true
        );

        sellCard.addView(sellTitle);

        sellCard.addView(text(
                french
                        ? "Publiez votre quantité, prix et photos."
                        : "انشر الكمية والسعر والصور.",
                14,
                TEXT,
                false
        ));

        space(sellCard, 8);

        Button sell = button(
                french ? "Publier une offre" : "إضافة عرض بيع",
                ORANGE
        );

        sell.setOnClickListener(v -> showSell());

        sellCard.addView(
                sell,
                new LinearLayout.LayoutParams(-1, 50)
        );

        content.addView(sellCard);

        space(content, 12);

        // FEATURES
        LinearLayout feature = card();

        TextView featureTitle = text(
                french
                        ? "Pourquoi DATRIDHA ?"
                        : "لماذا DATRIDHA؟",
                19,
                BROWN,
                true
        );

        feature.addView(featureTitle);

        feature.addView(text(
                "✓ " +
                        (french
                                ? "Commerce direct"
                                : "تجارة مباشرة"),
                15,
                TEXT,
                false
        ));

        feature.addView(text(
                "✓ " +
                        (french
                                ? "Qualité Deglet Nour"
                                : "جودة دقلة النور"),
                15,
                TEXT,
                false
        ));

        feature.addView(text(
                "✓ " +
                        (french
                                ? "Producteurs de Bechni"
                                : "منتجو بشني"),
                15,
                TEXT,
                false
        ));

        feature.addView(text(
                "✓ " +
                        (french
                                ? "Contact rapide"
                                : "تواصل سريع"),
                15,
                TEXT,
                false
        ));

        content.addView(feature);

        space(content, 15);

        TextView footer = text(
                french
                        ? "🌴 DATRIDHA — Plus qu’une application, une opportunité."
                        : "🌴 DATRIDHA — أكثر من تطبيق، فرصة تجارية.",
                13,
                GREEN,
                true
        );

        footer.setGravity(Gravity.CENTER);
        content.addView(footer);
    }

    // =========================================================
    // BUY PAGE
    // =========================================================

    void showBuy() {

        clearContent();

        addBack();

        TextView title = text(
                french
                        ? "Offres d'achat"
                        : "عروض الشراء",
                26,
                GREEN,
                true
        );

        title.setGravity(Gravity.CENTER);
        content.addView(title);

        space(content, 8);

        content.addView(text(
                french
                        ? "Deglet Nour disponible en gros."
                        : "دقلة النور المتوفرة بالجملة.",
                14,
                TEXT,
                false
        ));

        space(content, 14);

        addOffer(
                "🌴 Deglet Nour — Bechni",
                french
                        ? "Origine : Bechni"
                        : "المصدر: بشني",
                french
                        ? "Quantité : selon demande"
                        : "الكمية: حسب الطلب"
        );

        space(content, 10);

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

        offer.addView(text(
                title,
                19,
                BROWN,
                true
        ));

        offer.addView(text(
                line1,
                14,
                TEXT,
                false
        ));

        offer.addView(text(
                line2,
                14,
                TEXT,
                false
        ));

        space(offer, 8);

        Button details = button(
                french
                        ? "Demander cette offre"
                        : "طلب هذا العرض",
                GREEN
        );

        details.setOnClickListener(v -> {

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
        });

        offer.addView(
                details,
                new LinearLayout.LayoutParams(-1, 50)
        );

        content.addView(offer);
    }

    // =========================================================
    // SELL PAGE
    // =========================================================

    void showSell() {

        clearContent();

        addBack();

        TextView title = text(
                french
                        ? "Publier une offre"
                        : "إضافة عرض بيع",
                26,
                ORANGE,
                true
        );

        title.setGravity(Gravity.CENTER);
        content.addView(title);

        space(content, 10);

        content.addView(text(
                french
                        ? "Présentez votre Deglet Nour aux acheteurs."
                        : "اعرض دقلة النور الخاصة بك للمشترين.",
                14,
                TEXT,
                false
        ));

        space(content, 14);

        EditText seller = input(
                french
                        ? "Nom du vendeur *"
                        : "اسم البائع *"
        );

        content.addView(
                seller,
                new LinearLayout.LayoutParams(-1, 60)
        );

        space(content, 8);

        EditText quantity = input(
                french
                        ? "Quantité (kg / tonnes) *"
                        : "الكمية (كغ / طن) *"
        );

        content.addView(
                quantity,
                new LinearLayout.LayoutParams(-1, 60)
        );

        space(content, 8);

        EditText price = input(
                french
                        ? "Prix proposé"
                        : "السعر المقترح"
        );

        content.addView(
                price,
                new LinearLayout.LayoutParams(-1, 60)
        );

        space(content, 8);

        EditText phone = input(
                french
                        ? "Téléphone *"
                        : "رقم الهاتف *"
        );

        content.addView(
                phone,
                new LinearLayout.LayoutParams(-1, 60)
        );

        space(content, 8);

        EditText description = input(
                french
                        ? "Qualité / emballage / détails"
                        : "الجودة / التعبئة / التفاصيل"
        );

        description.setMinHeight(110);

        content.addView(
                description,
                new LinearLayout.LayoutParams(-1, 110)
        );

        space(content, 12);

        Button photos = button(
                french
                        ? "📷 Ajouter des photos"
                        : "📷 إضافة صور",
                BROWN
        );

        photos.setOnClickListener(v -> chooseImages());

        content.addView(
                photos,
                new LinearLayout.LayoutParams(-1, 52)
        );

        space(content, 14);

        Button publish = button(
                french
                        ? "Publier l'offre"
                        : "نشر العرض",
                ORANGE
        );

        publish.setOnClickListener(v -> {

            if (seller.getText().toString().trim().isEmpty()
                    || quantity.getText().toString().trim().isEmpty()
                    || phone.getText().toString().trim().isEmpty()) {

                Toast.makeText(
                        this,
                        french
                                ? "Veuillez remplir les champs *."
                                : "يرجى ملء الخانات التي عليها *.",
                        Toast.LENGTH_LONG
                ).show();

                return;
            }

            orders.add(
                    french
                            ? "Offre de vente publiée"
                            : "عرض بيع منشور"
            );

            Toast.makeText(
                    this,
                    french
                            ? "Offre enregistrée avec succès."
                            : "تم تسجيل العرض بنجاح.",
                    Toast.LENGTH_LONG
            ).show();

            showOrders();
        });

        content.addView(
                publish,
                new LinearLayout.LayoutParams(-1, 58)
        );
    }

    // =========================================================
    // PHOTOS
    // =========================================================

    void chooseImages() {

        Intent intent = new Intent(
                Intent.ACTION_OPEN_DOCUMENT
        );

        intent.setType("image/*");

        intent.putExtra(
                Intent.EXTRA_ALLOW_MULTIPLE,
                true
        );

        intent.addCategory(
                Intent.CATEGORY_OPENABLE
        );

        try {
            startActivityForResult(intent, 500);
        } catch (Exception e) {

            Toast.makeText(
                    this,
                    french
                            ? "Impossible d'ouvrir les photos."
                            : "تعذر فتح الصور.",
                    Toast.LENGTH_SHORT
            ).show();
        }
    }

    // =========================================================
    // ORDERS
    // =========================================================

    void showOrders() {

        clearContent();

        addBack();

        TextView title = text(
                french
                        ? "Mes commandes"
                        : "الطلبات",
                26,
                BROWN,
                true
        );

        title.setGravity(Gravity.CENTER);
        content.addView(title);

        space(content, 14);

        if (orders.isEmpty()) {

            LinearLayout empty = card();

            TextView icon = text(
                    "📋",
                    50,
                    BROWN,
                    false
            );

            icon.setGravity(Gravity.CENTER);
            empty.addView(icon);

            TextView message = text(
                    french
                            ? "Aucune commande pour le moment."
                            : "لا توجد طلبات حاليًا.",
                    17,
                    TEXT,
                    true
            );

            message.setGravity(Gravity.CENTER);

            empty.addView(message);

            content.addView(empty);

        } else {

            for (String order : orders) {

                LinearLayout item = card();

                item.addView(text(
                        "✓  " + order,
                        17,
                        GREEN,
                        true
                ));

                item.addView(text(
                        french
                                ? "Statut : enregistré"
                                : "الحالة: مسجل",
                        14,
                        TEXT,
                        false
                ));

                content.addView(item);

                space(content, 8);
            }
        }

        space(content, 14);

        Button newBuy = button(
                french
                        ? "Nouvel achat"
                        : "طلب شراء جديد",
                GREEN
        );

        newBuy.setOnClickListener(v -> showBuy());

        content.addView(
                newBuy,
                new LinearLayout.LayoutParams(-1, 52)
        );
    }

    // =========================================================
    // CONTACT
    // =========================================================

    void showContact() {

        clearContent();

        addBack();

        TextView title = text(
                french
                        ? "Contactez-nous"
                        : "تواصل معنا",
                27,
                DARK_GREEN,
                true
        );

        title.setGravity(Gravity.CENTER);

        content.addView(title);

        space(content, 14);

        LinearLayout contactCard = card();

        TextView palm = text(
                "🌴",
                55,
                GREEN,
                false
        );

        palm.setGravity(Gravity.CENTER);

        contactCard.addView(palm);

        TextView name = text(
                "DATRIDHA",
                30,
                BROWN,
                true
        );

        name.setGravity(Gravity.CENTER);

        contactCard.addView(name);

        space(contactCard, 8);

        TextView phone = text(
                "+216 51 022 448",
                19,
                DARK_GREEN,
                true
        );

        phone.setGravity(Gravity.CENTER);

        contactCard.addView(phone);

        TextView email = text(
                "ridhatouil1992@gmail.com",
                15,
                BROWN,
                false
        );

        email.setGravity(Gravity.CENTER);

        contactCard.addView(email);

        content.addView(contactCard);

        space(content, 14);

        Button call = button(
                french
                        ? "📞 Appeler"
                        : "📞 اتصال",
                GREEN
        );

        call.setOnClickListener(v -> {

            Intent intent = new Intent(
                    Intent.ACTION_DIAL,
                    Uri.parse("tel:+21651022448")
            );

            startActivity(intent);
        });

        content.addView(
                call,
                new LinearLayout.LayoutParams(-1, 54)
        );

        space(content, 8);

        Button mail = button(
                french
                        ? "✉ Envoyer un email"
                        : "✉ إرسال بريد إلكتروني",
                BROWN
        );

        mail.setOnClickListener(v -> {

            Intent intent = new Intent(
                    Intent.ACTION_SENDTO
            );

            intent.setData(
                    Uri.parse(
                            "mailto:ridhatouil1992@gmail.com"
                    )
            );

            intent.putExtra(
                    Intent.EXTRA_SUBJECT,
                    "DATRIDHA"
            );

            try {
                startActivity(intent);
            } catch (Exception e) {

                Toast.makeText(
                        this,
                        french
                                ? "Aucune application email."
                                : "لا يوجد تطبيق بريد إلكتروني.",
                        Toast.LENGTH_SHORT
                ).show();
            }
        });

        content.addView(
                mail,
                new LinearLayout.LayoutParams(-1, 54)
        );
    }

    // =========================================================
    // BACK
    // =========================================================

    void addBack() {

        Button back = button(
                french
                        ? "← Accueil"
                        : "← الرئيسية",
                DARK_BROWN
        );

        back.setOnClickListener(
                v -> showHome()
        );

        content.addView(
                back,
                new LinearLayout.LayoutParams(-1, 48)
        );

        space(content, 10);
    }
}
