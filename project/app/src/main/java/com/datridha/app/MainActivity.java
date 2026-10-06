package com.datridha.app;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.view.Gravity;
import android.widget.*;

import java.util.ArrayList;

public class MainActivity extends Activity {

    LinearLayout root;
    LinearLayout content;

    boolean french = false;

    ArrayList<String> orders = new ArrayList<>();

    // =========================================================
    // COLORS
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
    final int TEXT = Color.rgb(62, 55, 45);
    final int MUTED = Color.rgb(120, 110, 95);

    final int WHITE = Color.WHITE;

    // =========================================================
    // START
    // =========================================================

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showHome();
    }

    // =========================================================
    // HELPERS
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
                        ? Typeface.DEFAULT_BOLD
                        : Typeface.DEFAULT
        );

        t.setGravity(Gravity.CENTER_VERTICAL);

        t.setPadding(
                6,
                5,
                6,
                5
        );

        return t;
    }

    GradientDrawable bg(
            int color,
            float radius
    ) {

        GradientDrawable g =
                new GradientDrawable();

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

        GradientDrawable g =
                new GradientDrawable();

        g.setColor(color);
        g.setCornerRadius(radius);
        g.setStroke(
                strokeWidth,
                strokeColor
        );

        return g;
    }

    GradientDrawable brandGradient() {

        return new GradientDrawable(
                GradientDrawable.Orientation.TL_BR,
                new int[]{
                        Color.rgb(255, 250, 235),
                        Color.rgb(245, 230, 187),
                        Color.rgb(222, 190, 107)
                }
        );
    }

    LinearLayout vertical() {

        LinearLayout l =
                new LinearLayout(this);

        l.setOrientation(
                LinearLayout.VERTICAL
        );

        return l;
    }

    LinearLayout horizontal() {

        LinearLayout l =
                new LinearLayout(this);

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

        Space s =
                new Space(this);

        parent.addView(
                s,
                new LinearLayout.LayoutParams(
                        1,
                        height
                )
        );
    }

    Button button(
            String title,
            int color
    ) {

        Button b =
                new Button(this);

        b.setText(title);
        b.setTextColor(WHITE);
        b.setTextSize(13);
        b.setTypeface(
                Typeface.DEFAULT_BOLD
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
                bg(
                        color,
                        45
                )
        );

        return b;
    }

    EditText input(
            String hint
    ) {

        EditText e =
                new EditText(this);

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

        LinearLayout c =
                vertical();

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

    void clearContent() {

        if (content != null) {
            content.removeAllViews();
        }
    }

    // =========================================================
    // HOME
    // =========================================================

    void showHome() {

        root = vertical();

        root.setBackground(
                bg(
                        CREAM,
                        0
                )
        );

        // TOP BAR
        LinearLayout top =
                horizontal();

        top.setPadding(
                14,
                8,
                14,
                8
        );

        top.setBackground(
                bg(
                        WHITE,
                        0
                )
        );

        LinearLayout logoBox =
                horizontal();

        TextView palm =
                text(
                        "🌴",
                        25,
                        GREEN,
                        false
                );

        logoBox.addView(
                palm,
                new LinearLayout.LayoutParams(
                        38,
                        52
                )
        );

        TextView logo =
                text(
                        "DATRIDHA",
                        22,
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

        Button language =
                button(
                        french
                                ? "عربي"
                                : "FR",
                        GREEN
                );

        top.addView(
                language,
                new LinearLayout.LayoutParams(
                        62,
                        44
                )
        );

        language.setOnClickListener(
                v -> {

                    french = !french;

                    showHome();
                }
        );

        root.addView(top);

        // HERO
        LinearLayout hero =
                vertical();

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
                brandGradient()
        );

        TextView bigPalm =
                text(
                        "🌴",
                        58,
                        GREEN,
                        false
                );

        bigPalm.setGravity(
                Gravity.CENTER
        );

        hero.addView(bigPalm);

        TextView brand =
                text(
                        "DATRIDHA",
                        38,
                        DARK_BROWN,
                        true
                );

        brand.setGravity(
                Gravity.CENTER
        );

        hero.addView(brand);

        TextView subtitle =
                text(
                        french
                                ? "DEGLET NOUR • BECHNI"
                                : "دَقْلَةُ النُّور • بَشْنِي",
                        16,
                        GREEN,
                        true
                );

        subtitle.setGravity(
                Gravity.CENTER
        );

        hero.addView(subtitle);

        gap(
                hero,
                4
        );

        TextView slogan =
                text(
                        french
                                ? "Plus qu'une application... une opportunité !"
                                : "أكثر من تطبيق... فرصة تجارية !",
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
                        225
                )
        );

        // CONTENT
        ScrollView scroll =
                new ScrollView(this);

        content =
                vertical();

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

        createNavigation();

        setContentView(root);
    }

    // =========================================================
    // HOME CONTENT
    // =========================================================

    void showHomeContent() {

        clearContent();

        TextView welcome =
                text(
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

        TextView intro =
                text(
                        french
                                ? "La plateforme de commerce de gros de Deglet Nour."
                                : "منصة تجارة الجملة لدقلة النور.",
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
                16
        );

        addHomeCard(
                "🛒",
                french
                        ? "Acheter Deglet Nour"
                        : "شراء دقلة النور",
                french
                        ? "Découvrez les offres disponibles."
                        : "اكتشف عروض دقلة النور المتوفرة.",
                french
                        ? "Voir les offres"
                        : "مشاهدة العروض",
                GREEN,
                () -> showBuy()
        );

        gap(
                content,
                12
        );

        addHomeCard(
                "📦",
                french
                        ? "Vendre Deglet Nour"
                        : "بيع دقلة النور",
                french
                        ? "Publiez votre quantité, prix et photos."
                        : "انشر الكمية والسعر والصور.",
                french
                        ? "Publier une offre"
                        : "إضافة عرض بيع",
                ORANGE,
                () -> showSell()
        );

        gap(
                content,
                12
        );

        LinearLayout why =
                card();

        why.addView(
                text(
                        french
                                ? "Pourquoi DATRIDHA ?"
                                : "لماذا DATRIDHA؟",
                        19,
                        BROWN,
                        true
                )
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

        TextView footer =
                text(
                        "🌴 DATRIDHA",
                        18,
                        GREEN,
                        true
                );

        footer.setGravity(
                Gravity.CENTER
        );

        content.addView(footer);

        TextView footer2 =
                text(
                        french
                                ? "Plus qu'une application... une opportunité !"
                                : "أكثر من تطبيق... فرصة تجارية !",
                        12,
                        MUTED,
                        false
                );

        footer2.setGravity(
                Gravity.CENTER
        );

        content.addView(footer2);
    }

    void addHomeCard(
            String icon,
            String title,
            String description,
            String buttonText,
            int color,
            Runnable action
    ) {

        LinearLayout c =
                card();

        c.addView(
                text(
                        icon + "  " + title,
                        20,
                        color,
                        true
                )
        );

        gap(
                c,
                4
        );

        c.addView(
                text(
                        description,
                        14,
                        TEXT,
                        false
                )
        );

        gap(
                c,
                10
        );

        Button b =
                button(
                        buttonText,
                        color
                );

        b.setOnClickListener(
                v -> action.run()
        );

        c.addView(
                b,
                new LinearLayout.LayoutParams(
                        -1,
                        52
                )
        );

        content.addView(c);
    }

    void addFeature(
            LinearLayout parent,
            String value
    ) {

        parent.addView(
                text(
                        "✓  " + value,
                        15,
                        TEXT,
                        false
                )
        );
    }

    // =========================================================
    // NAVIGATION
    // =========================================================

    void createNavigation() {

        LinearLayout nav =
                horizontal();

        nav.setPadding(
                5,
                6,
                5,
                7
        );

        nav.setBackground(
                bg(
                        WHITE,
                        0
                )
        );

        Button contact =
                button(
                        french
                                ? "☎\nContact"
                                : "☎\nتواصل",
                        DARK_GREEN
                );

        Button ordersButton =
                button(
                        french
                                ? "📋\nCommandes"
                                : "📋\nالطلبات",
                        BROWN
                );

        Button sell =
                button(
                        french
                                ? "📦\nVendre"
                                : "📦\nبيع",
                        ORANGE
                );

        Button buy =
                button(
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
    // BUY
    // =========================================================

    void showBuy() {

        clearContent();

        addBack();

        TextView title =
                text(
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
                8
        );

        TextView info =
                text(
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
                "🌴 Deglet Nour - Bechni",
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

        LinearLayout offer =
                card();

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

        Button request =
                button(
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
    // SELL
    // =========================================================

    void showSell() {

        clearContent();

        addBack();

        TextView title =
                text(
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
                8
        );

        content.addView(
                text(
                        french
                                ? "Publiez votre offre pour les acheteurs."
                                : "انشر عرضك ليصل إلى المشترين.",
                        14,
                        TEXT,
                        false
                )
        );

        gap(
                content,
                14
        );

        EditText seller =
                input(
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

        EditText quantity =
                input(
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

        EditText price =
                input(
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

        EditText phone =
                input(
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

        EditText description =
                input(
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

        Button photos =
                button(
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

        Button publish =
                button(
                        french
                                ? "Publier l'offre"
                                : "نشر العرض",
                        ORANGE
                );

        publish.setOnClickListener(
                v -> {

                    if (
                            seller.getText()
                                    .toString()
                                    .trim()
                                    .isEmpty()
                            ||
                            quantity.getText()
                                    .toString()
                                    .trim()
                                    .isEmpty()
                            ||
                            phone.getText()
                                    .toString()
                                    .trim()
                                    .isEmpty()
                    ) {

                        Toast.makeText(
                                this,
                                french
                                        ? "Veuillez remplir les champs *."
                                        : "يرجى ملء الخانات المطلوبة *.",
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
                                    ? "Offre publiée avec succès."
                                    : "تم نشر العرض بنجاح.",
                            Toast.LENGTH_LONG
                    ).show();

                    showOrders();
                }
        );

        content.addView(
                publish,
                new LinearLayout.LayoutParams(
                        -1,
                        58
                )
        );
    }

    // =========================================================
    // IMAGE PICKER
    // =========================================================

    void chooseImages() {

        Intent intent =
                new Intent(
                        Intent.ACTION_OPEN_DOCUMENT
                );

        intent.setType(
                "image/*"
        );

        intent.putExtra(
                Intent.EXTRA_ALLOW_MULTIPLE,
                true
        );

        intent.addCategory(
                Intent.CATEGORY_OPENABLE
        );

        try {

            startActivityForResult(
                    intent,
                    500
            );

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

        TextView title =
                text(
                        french
                                ? "Mes commandes"
                                : "الطلبات",
                        26,
                        BROWN,
                        true
                );

        title.setGravity(
                Gravity.CENTER
        );

        content.addView(title);

        gap(
                content,
                15
        );

        if (orders.isEmpty()) {

            LinearLayout empty =
                    card();

            TextView icon =
                    text(
                            "📋",
                            52,
                            BROWN,
                            false
                    );

            icon.setGravity(
                    Gravity.CENTER
            );

            empty.addView(icon);

            TextView message =
                    text(
                            french
                                    ? "Aucune commande pour le moment."
                                    : "لا توجد طلبات حاليًا.",
                            17,
                            TEXT,
                            true
                    );

            message.setGravity(
                    Gravity.CENTER
            );

            empty.addView(message);

            content.addView(empty);

        } else {

            for (
                    String order
                    : orders
            ) {

                LinearLayout item =
                        card();

                item.addView(
                        text(
                                "✓  " + order,
                                17,
                                GREEN,
                                true
                        )
                );

                item.addView(
                        text(
                                french
                                        ? "Statut : enregistré"
                                        : "الحالة: مسجل",
                                14,
                                TEXT,
                                false
                        )
                );

                content.addView(item);

                gap(
                        content,
                        8
                );
            }
        }

        gap(
                content,
                14
        );

        Button newBuy =
                button(
                        french
                                ? "Nouvel achat"
                                : "طلب شراء جديد",
                        GREEN
                );

        newBuy.setOnClickListener(
                v -> showBuy()
        );

        content.addView(
                newBuy,
                new LinearLayout.LayoutParams(
                        -1,
                        54
                )
        );
    }

    // =========================================================
    // CONTACT
    // =========================================================

    void showContact() {

        clearContent();

        addBack();

        TextView title =
                text(
                        french
                                ? "Contactez-nous"
                                : "تواصل معنا",
                        27,
                        DARK_GREEN,
                        true
                );

        title.setGravity(
                Gravity.CENTER
        );

        content.addView(title);

        gap(
                content,
                15
        );

        LinearLayout contactCard =
                card();

        TextView palm =
                text(
                        "🌴",
                        58,
                        GREEN,
                        false
                );

        palm.setGravity(
                Gravity.CENTER
        );

        contactCard.addView(palm);

        TextView name =
                text(
                        "DATRIDHA",
                        31,
                        BROWN,
                        true
                );

        name.setGravity(
                Gravity.CENTER
        );

        contactCard.addView(name);

        gap(
                contactCard,
                10
        );

        TextView phone =
                text(
                        "+216 51 022 448",
                        19,
                        DARK_GREEN,
                        true
                );

        phone.setGravity(
                Gravity.CENTER
        );

        contactCard.addView(phone);

        TextView email =
                text(
                        "ridhatouil1992@gmail.com",
                        15,
                        BROWN,
                        false
                );

        email.setGravity(
                Gravity.CENTER
        );

        contactCard.addView(email);

        content.addView(contactCard);

        gap(
                content,
                14
        );

        Button call =
                button(
                        french
                                ? "📞  Appeler"
                                : "📞  اتصال",
                        GREEN
                );

        call.setOnClickListener(
                v -> {

                    Intent intent =
                            new Intent(
                                    Intent.ACTION_DIAL,
                                    Uri.parse(
                                            "tel:+21651022448"
                                    )
                            );

                    startActivity(intent);
                }
        );

        content.addView(
                call,
                new LinearLayout.LayoutParams(
                        -1,
                        55
                )
        );

        gap(
                content,
                9
        );

        Button mail =
                button(
                        french
                                ? "✉  Envoyer un email"
                                : "✉  إرسال بريد إلكتروني",
                        BROWN
                );

        mail.setOnClickListener(
                v -> {

                    Intent intent =
                            new Intent(
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
                }
        );

        content.addView(
                mail,
                new LinearLayout.LayoutParams(
                        -1,
                        55
                )
        );
    }

    // =========================================================
    // BACK
    // =========================================================

    void addBack() {

        Button back =
                button(
                        french
                                ? "<  Accueil"
                                : "<  الرئيسية",
                        DARK_BROWN
                );

        back.setOnClickListener(
                v -> showHome()
        );

        content.addView(
                back,
                new LinearLayout.LayoutParams(
                        -1,
                        48
                )
        );

        gap(
                content,
                10
        );
    }
}
