package com.datridha.app;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    // =========================
    // COLORS
    // =========================

    private static final int GREEN = Color.rgb(23, 107, 58);
    private static final int LIGHT_GREEN = Color.rgb(46, 139, 87);
    private static final int GOLD = Color.rgb(212, 175, 55);
    private static final int BROWN = Color.rgb(91, 58, 30);
    private static final int BEIGE = Color.rgb(247, 233, 215);
    private static final int WHITE = Color.WHITE;
    private static final int DARK = Color.rgb(45, 45, 45);
    private static final int BLUE = Color.rgb(45, 110, 190);
    private static final int YELLOW = Color.rgb(220, 170, 40);

    // =========================
    // APP STATE
    // =========================

    private LinearLayout root;
    private boolean french = false;

    private final List<String> orders = new ArrayList<>();

    // =========================
    // ACTIVITY
    // =========================

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        orders.add("Commande #001 - 2 tonnes - En cours");
        orders.add("Commande #002 - 5 tonnes - Acceptée");

        showSplash();
    }

    // =========================
    // BASIC UI
    // =========================

    private void createRoot() {
        root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setBackgroundColor(BEIGE);

        ScrollView scrollView = new ScrollView(this);
        scrollView.setFillViewport(true);

        scrollView.addView(root);

        setContentView(scrollView);
    }

    private TextView text(
            String value,
            float size,
            int color,
            boolean bold
    ) {
        TextView t = new TextView(this);

        t.setText(value);
        t.setTextSize(size);
        t.setTextColor(color);
        t.setGravity(Gravity.CENTER_VERTICAL);

        if (bold) {
            t.setTypeface(
                    Typeface.create(
                            Typeface.DEFAULT,
                            Typeface.BOLD
                    )
            );
        }

        t.setPadding(20, 15, 20, 15);

        return t;
    }

    private Button button(
            String title,
            int color
    ) {
        Button b = new Button(this);

        b.setText(title);
        b.setTextColor(WHITE);
        b.setTextSize(15);
        b.setAllCaps(false);
        b.setBackgroundColor(color);

        LinearLayout.LayoutParams p =
                new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                );

        p.setMargins(20, 8, 20, 8);

        b.setLayoutParams(p);

        return b;
    }

    private LinearLayout card() {

        LinearLayout c = new LinearLayout(this);

        c.setOrientation(LinearLayout.VERTICAL);
        c.setPadding(15, 15, 15, 15);
        c.setBackgroundColor(WHITE);

        LinearLayout.LayoutParams p =
                new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                );

        p.setMargins(15, 10, 15, 10);

        c.setLayoutParams(p);

        return c;
    }

    private TextView cardText(String value) {

        TextView t = text(
                value,
                15,
                DARK,
                false
        );

        t.setBackgroundColor(WHITE);

        return t;
    }

    private EditText input(
            String hint
    ) {

        EditText e = new EditText(this);

        e.setHint(hint);
        e.setTextSize(15);
        e.setPadding(20, 15, 20, 15);
        e.setBackgroundColor(WHITE);

        LinearLayout.LayoutParams p =
                new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                );

        p.setMargins(15, 7, 15, 7);

        e.setLayoutParams(p);

        return e;
    }

    // =========================
    // HEADER
    // =========================

    private void header() {

        LinearLayout h = new LinearLayout(this);

        h.setOrientation(LinearLayout.HORIZONTAL);
        h.setGravity(Gravity.CENTER_VERTICAL);
        h.setPadding(15, 12, 15, 12);
        h.setBackgroundColor(GREEN);

        TextView logo = text(
                "🌴 DATRIDHA",
                23,
                WHITE,
                true
        );

        h.addView(
                logo,
                new LinearLayout.LayoutParams(
                        0,
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        1
                )
        );

        Button lang = new Button(this);

        lang.setText(
                french ? "عربي" : "FR"
        );

        lang.setTextColor(WHITE);
        lang.setAllCaps(false);
        lang.setBackgroundColor(LIGHT_GREEN);

        lang.setOnClickListener(v -> {

            french = !french;

            showHome();
        });

        h.addView(lang);

        root.addView(h);
    }

    // =========================
    // BASE PAGE
    // =========================

    private void base(
            String title,
            String subtitle
    ) {

        createRoot();

        header();

        TextView t = text(
                title,
                26,
                GREEN,
                true
        );

        t.setGravity(Gravity.CENTER);

        root.addView(t);

        TextView s = text(
                subtitle,
                15,
                BROWN,
                false
        );

        s.setGravity(Gravity.CENTER);

        root.addView(s);
    }

    // =========================
    // SPLASH
    // =========================

    private void showSplash() {

        createRoot();

        root.setGravity(Gravity.CENTER);
        root.setBackgroundColor(GREEN);

        TextView palm = text(
                "🌴",
                60,
                WHITE,
                false
        );

        palm.setGravity(Gravity.CENTER);

        root.addView(palm);

        TextView logo = text(
                "DATRIDHA",
                38,
                WHITE,
                true
        );

        logo.setGravity(Gravity.CENTER);

        root.addView(logo);

        TextView sub = text(
                french ?
                        "Deglet Nour de Bechni" :
                        "دقلة النور من بشني",
                18,
                GOLD,
                true
        );

        sub.setGravity(Gravity.CENTER);

        root.addView(sub);

        Button start = button(
                french ? "Entrer" : "الدخول",
                GOLD
        );

        start.setOnClickListener(
                v -> showHome()
        );

        root.addView(start);
    }

    // =========================
    // HOME
    // =========================

    private void showHome() {

        base(
                "🌴 DATRIDHA",
                french ?
                        "Deglet Nour de Bechni" :
                        "دقلة النور من بشني"
        );

        TextView welcome = text(
                french ?
                        "Commerce direct de Deglet Nour en gros" :
                        "تجارة دقلة النور بالجملة مباشرة من المنتج",
                18,
                BROWN,
                true
        );

        welcome.setGravity(Gravity.CENTER);

        root.addView(welcome);

        // BUY

        Button buy = button(
                "🛒 " +
                        (french ?
                                "Acheter Deglet Nour" :
                                "شراء دقلة النور"),
                GREEN
        );

        buy.setOnClickListener(
                v -> showBuy()
        );

        root.addView(buy);

        // SELL

        Button sell = button(
                "📦 " +
                        (french ?
                                "Vendre Deglet Nour" :
                                "بيع دقلة النور"),
                GOLD
        );

        sell.setTextColor(BROWN);

        sell.setOnClickListener(
                v -> showSell()
        );

        root.addView(sell);

        // ORDERS

        Button ordersButton = button(
                "📋 " +
                        (french ?
                                "Mes commandes" :
                                "الطلبات"),
                LIGHT_GREEN
        );

        ordersButton.setOnClickListener(
                v -> showOrders()
        );

        root.addView(ordersButton);

        // CONTACT

        Button contact = button(
                "☎ " +
                        (french ?
                                "Contact" :
                                "تواصل معنا"),
                BROWN
        );

        contact.setOnClickListener(
                v -> showContact()
        );

        root.addView(contact);

        // PRODUCT CARD

        LinearLayout product = card();

        TextView ptitle = text(
                "🌴 " +
                        (french ?
                                "Deglet Nour - Bechni" :
                                "دقلة النور - بشني"),
                20,
                GREEN,
                true
        );

        product.addView(ptitle);

        product.addView(
                cardText(
                        french ?
                                "Qualité supérieure • Vente en gros" :
                                "جودة ممتازة • بيع بالجملة"
                )
        );

        product.addView(
                cardText(
                        french ?
                                "Origine : Bechni - El Fawar - Kebili" :
                                "المصدر: بشني - الفوار - قبلي"
                )
        );

        root.addView(product);

        // TRUST BADGES

        TextView badges = text(
                "✓ " +
                        (french ? "Fiable" : "موثوق") +
                        "     •     " +
                        "✓ " +
                        (french ? "Qualité" : "جودة") +
                        "     •     " +
                        "✓ " +
                        (french ? "Commerce direct" : "تجارة مباشرة"),
                14,
                GREEN,
                true
        );

        badges.setGravity(Gravity.CENTER);

        root.addView(badges);

        addBottomNav();
    }

    // =========================
    // BUY
    // =========================

    private void showBuy() {

        base(
                french ?
                        "Acheter Deglet Nour" :
                        "شراء دقلة النور",
                french ?
                        "Offres disponibles en gros" :
                        "العروض المتوفرة بالجملة"
        );

        EditText search = input(
                french ?
                        "Rechercher une offre..." :
                        "ابحث عن عرض..."
        );

        root.addView(search);

        TextView filters = text(
                french ?
                        "Filtres : Toutes • Quantité • Prix • Région" :
                        "الفلاتر: الكل • الكمية • السعر • المنطقة",
                14,
                BROWN,
                true
        );

        root.addView(filters);

        // OFFER 1

        LinearLayout offer1 = card();

        offer1.addView(
                text(
                        "🌴 Deglet Nour - Bechni",
                        19,
                        GREEN,
                        true
                )
        );

        offer1.addView(
                cardText(
                        french ?
                                "Quantité : 2 tonnes" :
                                "الكمية: 2 طن"
                )
        );

        offer1.addView(
                cardText(
                        french ?
                                "Prix : Sur demande" :
                                "السعر: عند الطلب"
                )
        );

        offer1.addView(
                cardText(
                        french ?
                                "Région : Bechni, Kebili" :
                                "المنطقة: بشني، قبلي"
                )
        );

        Button details = button(
                french ?
                        "Voir les détails" :
                        "تفاصيل العرض",
                GREEN
        );

        details.setOnClickListener(
                v -> showOfferDetails()
        );

        offer1.addView(details);

        root.addView(offer1);

        // OFFER 2

        LinearLayout offer2 = card();

        offer2.addView(
                text(
                        "🌴 Deglet Nour - Qualité Premium",
                        19,
                        GREEN,
                        true
                )
        );

        offer2.addView(
                cardText(
                        french ?
                                "Quantité : 5 tonnes" :
                                "الكمية: 5 طن"
                )
        );

        offer2.addView(
                cardText(
                        french ?
                                "Conditionnement : cartons" :
                                "التعبئة: صناديق"
                )
        );

        offer2.addView(
                cardText(
                        french ?
                                "Origine : Bechni" :
                                "المصدر: بشني"
                )
        );

        Button details2 = button(
                french ?
                        "Voir les détails" :
                        "تفاصيل العرض",
                LIGHT_GREEN
        );

        details2.setOnClickListener(
                v -> showOfferDetails()
        );

        offer2.addView(details2);

        root.addView(offer2);

        addBottomNav();
    }

    // =========================
    // SELL
    // =========================

    private void showSell() {

        base(
                french ?
                        "Publier une offre" :
                        "نشر عرض بيع",
                french ?
                        "Proposez votre Deglet Nour" :
                        "اعرض دقلة النور للبيع"
        );

        EditText name = input(
                french ?
                        "Nom du vendeur / entreprise" :
                        "اسم البائع / الشركة"
        );

        root.addView(name);

        EditText quantity = input(
                french ?
                        "Quantité en tonnes" :
                        "الكمية بالطن"
        );

        root.addView(quantity);

        EditText price = input(
                french ?
                        "Prix par kg" :
                        "السعر للكيلوغرام"
        );

        root.addView(price);

        EditText phone = input(
                french ?
                        "Numéro de téléphone" :
                        "رقم الهاتف"
        );

        root.addView(phone);

        EditText description = input(
                french ?
                        "Qualité, emballage et conditions..." :
                        "الجودة، التعبئة والشروط..."
        );

        description.setMinLines(4);

        root.addView(description);

        Button photos = button(
                "📷 " +
                        (french ?
                                "Ajouter des photos" :
                                "إضافة صور"),
                BROWN
        );

        photos.setOnClickListener(
                v -> choosePhotos()
        );

        root.addView(photos);

        Button publish = button(
                "✓ " +
                        (french ?
                                "Publier l'offre" :
                                "نشر العرض"),
                GREEN
        );

        publish.setOnClickListener(v -> {

            Toast.makeText(
                    this,
                    french ?
                            "Offre publiée avec succès" :
                            "تم نشر العرض بنجاح",
                    Toast.LENGTH_LONG
            ).show();

            showHome();
        });

        root.addView(publish);

        addBottomNav();
    }

    // =========================
    // PHOTO PICKER
    // =========================

    private void choosePhotos() {

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

            startActivityForResult(
                    intent,
                    100
            );

        } catch (Exception e) {

            Toast.makeText(
                    this,
                    french ?
                            "Impossible d'ouvrir les photos" :
                            "تعذر فتح الصور",
                    Toast.LENGTH_SHORT
            ).show();
        }
    }

    // =========================
    // ORDERS
    // =========================

    private void showOrders() {

        base(
                french ?
                        "Mes commandes" :
                        "الطلبات",
                french ?
                        "Historique et suivi" :
                        "متابعة الطلبات والسجل"
        );

        TextView filter = text(
                french ?
                        "Toutes • En cours • Acceptées • Reçues" :
                        "الكل • قيد التنفيذ • مقبولة • مستلمة",
                14,
                BROWN,
                true
        );

        filter.setGravity(Gravity.CENTER);

        root.addView(filter);

        if (orders.isEmpty()) {

            root.addView(
                    cardText(
                            french ?
                                    "Aucune commande." :
                                    "لا توجد طلبات حاليًا."
                    )
            );

        } else {

            for (String order : orders) {

                LinearLayout c = card();

                c.addView(
                        text(
                                "📦 " + order,
                                17,
                                GREEN,
                                true
                        )
                );

                Button view = button(
                        french ?
                                "Voir" :
                                "عرض",
                        LIGHT_GREEN
                );

                view.setOnClickListener(
                        v -> showOfferDetails()
                );

                c.addView(view);

                root.addView(c);
            }
        }

        addBottomNav();
    }

    // =========================
    // CONTACT
    // =========================

    private void showContact() {

        base(
                french ?
                        "Contact" :
                        "تواصل معنا",
                french ?
                        "L'équipe DATRIDHA est à votre disposition" :
                        "فريق DATRIDHA في خدمتكم"
        );

        LinearLayout contactCard = card();

        contactCard.addView(
                text(
                        "📞 +216 51022448",
                        18,
                        GREEN,
                        true
                )
        );

        contactCard.addView(
                text(
                        "✉ ridhatouil1992@gmail.com",
                        16,
                        BROWN,
                        false
                )
        );

        contactCard.addView(
                text(
                        french ?
                                "DATRIDHA - Deglet Nour de Bechni" :
                                "DATRIDHA - دقلة النور من بشني",
                        16,
                        DARK,
                        true
                )
        );

        root.addView(contactCard);

        Button call = button(
                "📞 " +
                        (french ?
                                "Appeler" :
                                "اتصال"),
                GREEN
        );

        call.setOnClickListener(v -> {

            try {

                Intent intent = new Intent(
                        Intent.ACTION_DIAL,
                        Uri.parse(
                                "tel:+21651022448"
                        )
                );

                startActivity(intent);

            } catch (Exception e) {

                Toast.makeText(
                        this,
                        french ?
                                "Impossible d'ouvrir le téléphone" :
                                "تعذر فتح الهاتف",
                        Toast.LENGTH_SHORT
                ).show();
            }
        });

        root.addView(call);

        Button email = button(
                "✉ " +
                        (french ?
                                "Envoyer un email" :
                                "إرسال بريد إلكتروني"),
                BROWN
        );

        email.setOnClickListener(v -> {

            try {

                Intent intent =
                        new Intent(
                                Intent.ACTION_SENDTO
                        );

                intent.setData(
                        Uri.parse(
                                "mailto:ridhatouil1992@gmail.com"
                        )
                );

                startActivity(intent);

            } catch (Exception e) {

                Toast.makeText(
                        this,
                        french ?
                                "Impossible d'ouvrir l'email" :
                                "تعذر فتح البريد الإلكتروني",
                        Toast.LENGTH_SHORT
                ).show();
            }
        });

        root.addView(email);

        addBottomNav();
    }

    // =========================
    // SETTINGS
    // =========================

    private void showSettings() {

        base(
                french ?
                        "Paramètres" :
                        "الإعدادات",
                french ?
                        "Personnalisez DATRIDHA" :
                        "تخصيص تطبيق DATRIDHA"
        );

        LinearLayout languageCard = card();

        languageCard.addView(
                text(
                        "🌐 " +
                                (french ?
                                        "Langue" :
                                        "اللغة"),
                        18,
                        GREEN,
                        true
                )
        );

        Button language = button(
                french ?
                        "العربية" :
                        "Français",
                LIGHT_GREEN
        );

        language.setOnClickListener(v -> {

            french = !french;

            showSettings();
        });

        languageCard.addView(language);

        root.addView(languageCard);

        LinearLayout notification = card();

        notification.addView(
                text(
                        "🔔 " +
                                (french ?
                                        "Notifications" :
                                        "الإشعارات"),
                        17,
                        DARK,
                        true
                )
        );

        Switch notificationSwitch =
                new Switch(this);

        notificationSwitch.setText(
                french ?
                        "Activer les notifications" :
                        "تفعيل الإشعارات"
        );

        notification.addView(
                notificationSwitch
        );

        root.addView(notification);

        LinearLayout about = card();

        about.addView(
                text(
                        "ℹ " +
                                (french ?
                                        "À propos de DATRIDHA" :
                                        "حول DATRIDHA"),
                        17,
                        GREEN,
                        true
                )
        );

        about.addView(
                cardText(
                        french ?
                                "Plateforme de commerce en gros de Deglet Nour de Bechni." :
                                "منصة لتجارة دقلة النور بالجملة من بشني."
                )
        );

        root.addView(about);

        LinearLayout privacy = card();

        privacy.addView(
                text(
                        "🔒 " +
                                (french ?
                                        "Confidentialité" :
                                        "الخصوصية"),
                        17,
                        DARK,
                        true
                )
        );

        root.addView(privacy);

        LinearLayout help = card();

        help.addView(
                text(
                        "❓ " +
                                (french ?
                                        "Aide" :
                                        "المساعدة"),
                        17,
                        DARK,
                        true
                )
        );

        root.addView(help);

        addBottomNav();
    }

    // =========================
    // OFFER DETAILS
    // =========================

    private void showOfferDetails() {

        base(
                french ?
                        "Détails de l'offre" :
                        "تفاصيل العرض",
                french ?
                        "Deglet Nour - Bechni" :
                        "دقلة النور - بشني"
        );

        LinearLayout product = card();

        TextView image = text(
                "🌴🌴🌴\n🌴  D E G L E T   N O U R  🌴\n🌴🌴🌴",
                24,
                GREEN,
                true
        );

        image.setGravity(Gravity.CENTER);
        image.setPadding(10, 40, 10, 40);

        product.addView(image);

        product.addView(
                text(
                        french ?
                                "Deglet Nour - Bechni" :
                                "دقلة النور - بشني",
                        23,
                        GREEN,
                        true
                )
        );

        product.addView(
                cardText(
                        french ?
                                "Prix : Sur demande" :
                                "السعر: عند الطلب"
                )
        );

        product.addView(
                cardText(
                        french ?
                                "Quantité : 2 tonnes" :
                                "الكمية: 2 طن"
                )
        );

        product.addView(
                cardText(
                        french ?
                                "Origine : Bechni - Kebili" :
                                "المصدر: بشني - قبلي"
                )
        );

        product.addView(
                cardText(
                        french ?
                                "Qualité : Premium" :
                                "الجودة: ممتازة"
                )
        );

        product.addView(
                cardText(
                        french ?
                                "Conditionnement : selon demande" :
                                "التعبئة: حسب الطلب"
                )
        );

        root.addView(product);

        Button favorite = button(
                "♡ " +
                        (french ?
                                "Ajouter aux favoris" :
                                "إضافة إلى المفضلة"),
                GOLD
        );

        favorite.setTextColor(BROWN);

        favorite.setOnClickListener(v -> {

            Toast.makeText(
                    this,
                    french ?
                            "Ajouté aux favoris" :
                            "تمت الإضافة إلى المفضلة",
                    Toast.LENGTH_SHORT
            ).show();
        });

        root.addView(favorite);

        Button contactOwner = button(
                "☎ " +
                        (french ?
                                "Contacter le vendeur" :
                                "التواصل مع صاحب العرض"),
                GREEN
        );

        contactOwner.setOnClickListener(
                v -> showContact()
        );

        root.addView(contactOwner);

        Button order = button(
                "🛒 " +
                        (french ?
                                "Commander" :
                                "طلب هذه الكمية"),
                BROWN
        );

        order.setOnClickListener(v -> {

            orders.add(
                    french ?
                            "Nouvelle commande - 2 tonnes - En cours" :
                            "طلب جديد - 2 طن - قيد التنفيذ"
            );

            Toast.makeText(
                    this,
                    french ?
                            "Commande enregistrée" :
                            "تم تسجيل الطلب",
                    Toast.LENGTH_LONG
            ).show();

            showOrders();
        });

        root.addView(order);

        addBottomNav();
    }

    // =========================
    // BOTTOM NAVIGATION
    // =========================

    private void addBottomNav() {

        LinearLayout nav = new LinearLayout(this);

        nav.setOrientation(
                LinearLayout.HORIZONTAL
        );

        nav.setGravity(
                Gravity.CENTER
        );

        nav.setPadding(
                5,
                8,
                5,
                8
        );

        nav.setBackgroundColor(
                GREEN
        );

        Button home = navButton(
                "⌂",
                french ?
                        "Accueil" :
                        "الرئيسية"
        );

        home.setOnClickListener(
                v -> showHome()
        );

        nav.addView(home);

        Button buy = navButton(
                "🛒",
                french ?
                        "Acheter" :
                        "شراء"
        );

        buy.setOnClickListener(
                v -> showBuy()
        );

        nav.addView(buy);

        Button order = navButton(
                "📋",
                french ?
                        "Commandes" :
                        "الطلبات"
        );

        order.setOnClickListener(
                v -> showOrders()
        );

        nav.addView(order);

        Button contact = navButton(
                "☎",
                french ?
                        "Contact" :
                        "تواصل"
        );

        contact.setOnClickListener(
                v -> showContact()
        );

        nav.addView(contact);

        Button settings = navButton(
                "⚙",
                french ?
                        "Réglages" :
                        "الإعدادات"
        );

        settings.setOnClickListener(
                v -> showSettings()
        );

        nav.addView(settings);

        root.addView(nav);
    }

    private Button navButton(
            String icon,
            String title
    ) {

        Button b = new Button(this);

        b.setText(
                icon + "\n" + title
        );

        b.setTextColor(WHITE);
        b.setTextSize(11);
        b.setAllCaps(false);
        b.setBackgroundColor(GREEN);

        b.setGravity(
                Gravity.CENTER
        );

        b.setPadding(
                4,
                4,
                4,
                4
        );

        b.setLayoutParams(
                new LinearLayout.LayoutParams(
                        0,
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        1
                )
        );

        return b;
    }

    // =========================
    // ACTIVITY RESULT
    // =========================

    @Override
    protected void onActivityResult(
            int requestCode,
            int resultCode,
            Intent data
    ) {

        super.onActivityResult(
                requestCode,
                resultCode,
                data
        );

        if (
                requestCode == 100 &&
                resultCode == RESULT_OK
        ) {

            Toast.makeText(
                    this,
                    french ?
                            "Photos sélectionnées" :
                            "تم اختيار الصور",
                    Toast.LENGTH_SHORT
            ).show();
        }
    }
}
