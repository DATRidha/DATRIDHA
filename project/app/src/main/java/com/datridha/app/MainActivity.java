package com.datridha.app;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.provider.Settings;
import android.view.Gravity;
import android.view.View;
import android.widget.*;
import java.util.ArrayList;

public class MainActivity extends Activity {

    LinearLayout root;
    LinearLayout content;
    boolean french = false;

    ArrayList<String> orders = new ArrayList<>();

    int brown = Color.rgb(82, 45, 20);
    int darkBrown = Color.rgb(55, 30, 15);
    int gold = Color.rgb(218, 164, 57);
    int green = Color.rgb(42, 105, 52);
    int darkGreen = Color.rgb(28, 73, 36);
    int cream = Color.rgb(250, 245, 231);
    int white = Color.WHITE;
    int textDark = Color.rgb(55, 45, 38);
    int orange = Color.rgb(194, 93, 31);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showHome();
    }

    // =========================================================
    // BASIC HELPERS
    // =========================================================

    TextView text(String value, float size, int color, boolean bold) {
        TextView t = new TextView(this);
        t.setText(value);
        t.setTextSize(size);
        t.setTextColor(color);
        t.setTypeface(bold ? Typeface.DEFAULT_BOLD : Typeface.DEFAULT);
        t.setGravity(Gravity.CENTER_VERTICAL);
        t.setPadding(8, 8, 8, 8);
        return t;
    }

    GradientDrawable background(int color, float radius) {
        GradientDrawable g = new GradientDrawable();
        g.setColor(color);
        g.setCornerRadius(radius);
        return g;
    }

    GradientDrawable gradientBackground() {
        GradientDrawable g = new GradientDrawable(
                GradientDrawable.Orientation.TL_BR,
                new int[]{
                        Color.rgb(255, 248, 226),
                        Color.rgb(239, 225, 184),
                        Color.rgb(218, 192, 126)
                }
        );
        return g;
    }

    Button button(String label, int color) {
        Button b = new Button(this);
        b.setText(label);
        b.setTextSize(15);
        b.setTextColor(Color.WHITE);
        b.setTypeface(Typeface.DEFAULT_BOLD);
        b.setAllCaps(false);
        b.setGravity(Gravity.CENTER);
        b.setPadding(10, 4, 10, 4);
        b.setBackground(background(color, 45));
        return b;
    }

    EditText input(String hint) {
        EditText e = new EditText(this);
        e.setHint(hint);
        e.setTextSize(15);
        e.setTextColor(textDark);
        e.setHintTextColor(Color.rgb(130, 120, 105));
        e.setSingleLine(false);
        e.setPadding(18, 12, 18, 12);
        e.setBackground(background(Color.WHITE, 28));
        return e;
    }

    LinearLayout vertical() {
        LinearLayout l = new LinearLayout(this);
        l.setOrientation(LinearLayout.VERTICAL);
        l.setPadding(18, 18, 18, 18);
        return l;
    }

    LinearLayout horizontal() {
        LinearLayout l = new LinearLayout(this);
        l.setOrientation(LinearLayout.HORIZONTAL);
        l.setGravity(Gravity.CENTER_VERTICAL);
        return l;
    }

    void addSpace(LinearLayout parent, int height) {
        Space s = new Space(this);
        parent.addView(s, new LinearLayout.LayoutParams(
                1, height
        ));
    }

    void clearContent() {
        content.removeAllViews();
    }

    // =========================================================
    // MAIN SCREEN
    // =========================================================

    void showHome() {

        root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setBackground(gradientBackground());

        // TOP BAR
        LinearLayout top = horizontal();
        top.setPadding(14, 14, 14, 8);

        TextView logo = text("DATRIDHA", 25, brown, true);
        logo.setGravity(Gravity.CENTER);

        top.addView(logo, new LinearLayout.LayoutParams(
                0, 60, 1
        ));

        Button lang = button(french ? "عربي" : "FR", darkGreen);
        lang.setTextSize(13);

        top.addView(lang, new LinearLayout.LayoutParams(
                65, 48
        ));

        lang.setOnClickListener(v -> {
            french = !french;
            showHome();
        });

        root.addView(top);

        // BRAND AREA
        LinearLayout brand = vertical();
        brand.setGravity(Gravity.CENTER);
        brand.setPadding(20, 18, 20, 18);
        brand.setBackground(background(Color.argb(245, 255, 251, 238), 38));

        TextView palm = text("🌴", 50, green, false);
        palm.setGravity(Gravity.CENTER);
        brand.addView(palm);

        TextView title = text(
                "DATRIDHA",
                38,
                darkBrown,
                true
        );
        title.setGravity(Gravity.CENTER);
        brand.addView(title);

        TextView subtitle = text(
                french
                        ? "Deglet Nour de Bechni"
                        : "دقلة النور من بشني",
                18,
                green,
                true
        );
        subtitle.setGravity(Gravity.CENTER);
        brand.addView(subtitle);

        TextView slogan = text(
                french
                        ? "Achetez • Vendez • Échangez"
                        : "اشترِ • بِع • تعامل",
                14,
                brown,
                false
        );
        slogan.setGravity(Gravity.CENTER);
        brand.addView(slogan);

        root.addView(brand, new LinearLayout.LayoutParams(
                -1, 205
        ));

        addSpace(root, 12);

        // CONTENT
        ScrollView scroll = new ScrollView(this);

        content = vertical();
        content.setPadding(18, 5, 18, 25);

        scroll.addView(content);
        root.addView(scroll, new LinearLayout.LayoutParams(
                -1, 0, 1
        ));

        // BOTTOM NAVIGATION
        LinearLayout nav = horizontal();
        nav.setPadding(8, 8, 8, 8);
        nav.setBackground(background(Color.WHITE, 0));

        Button buy = button(
                french ? "Acheter" : "شراء",
                green
        );

        Button sell = button(
                french ? "Vendre" : "بيع",
                orange
        );

        Button ordersBtn = button(
                french ? "Commandes" : "الطلبات",
                brown
        );

        Button contact = button(
                french ? "Contact" : "تواصل",
                darkGreen
        );

        nav.addView(buy, new LinearLayout.LayoutParams(
                0, 52, 1
        ));
        nav.addView(sell, new LinearLayout.LayoutParams(
                0, 52, 1
        ));
        nav.addView(ordersBtn, new LinearLayout.LayoutParams(
                0, 52, 1
        ));
        nav.addView(contact, new LinearLayout.LayoutParams(
                0, 52, 1
        ));

        buy.setOnClickListener(v -> showBuy());
        sell.setOnClickListener(v -> showSell());
        ordersBtn.setOnClickListener(v -> showOrders());
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
                23,
                darkBrown,
                true
        );

        welcome.setGravity(Gravity.CENTER);
        content.addView(welcome);

        addSpace(content, 8);

        TextView description = text(
                french
                        ? "La plateforme dédiée au commerce de gros de Deglet Nour de Bechni."
                        : "منصة مخصصة لتجارة الجملة لدقلة النور من بشني.",
                15,
                textDark,
                false
        );

        description.setGravity(Gravity.CENTER);
        content.addView(description);

        addSpace(content, 20);

        // BUY CARD
        LinearLayout buyCard = card();

        TextView buyTitle = text(
                "🛒  " + (french ? "Acheter Deglet Nour" : "شراء دقلة النور"),
                20,
                green,
                true
        );

        buyCard.addView(buyTitle);

        TextView buyText = text(
                french
                        ? "Découvrez les offres disponibles et contactez les vendeurs."
                        : "اكتشف عروض البيع المتوفرة وتواصل مع البائع.",
                14,
                textDark,
                false
        );

        buyCard.addView(buyText);

        Button buyButton = button(
                french ? "Voir les offres" : "مشاهدة العروض",
                green
        );

        buyButton.setOnClickListener(v -> showBuy());
        buyCard.addView(buyButton, new LinearLayout.LayoutParams(
                -1, 50
        ));

        content.addView(buyCard);

        addSpace(content, 12);

        // SELL CARD
        LinearLayout sellCard = card();

        TextView sellTitle = text(
                "📦  " + (french ? "Vendre Deglet Nour" : "بيع دقلة النور"),
                20,
                orange,
                true
        );

        sellCard.addView(sellTitle);

        TextView sellText = text(
                french
                        ? "Publiez votre quantité, votre prix et vos photos."
                        : "انشر الكمية والسعر وأرفق صور منتجك.",
                14,
                textDark,
                false
        );

        sellCard.addView(sellText);

        Button sellButton = button(
                french ? "Publier une offre" : "إضافة عرض بيع",
                orange
        );

        sellButton.setOnClickListener(v -> showSell());
        sellCard.addView(sellButton, new LinearLayout.LayoutParams(
                -1, 50
        ));

        content.addView(sellCard);

        addSpace(content, 12);

        // TRUST CARD
        LinearLayout trust = card();

        TextView trustTitle = text(
                french ? "🌴 Notre objectif" : "🌴 هدف DATRIDHA",
                19,
                brown,
                true
        );

        trust.addView(trustTitle);

        TextView trustText = text(
                french
                        ? "Faciliter le commerce de Deglet Nour entre producteurs, grossistes et acheteurs."
                        : "تسهيل التجارة في دقلة النور بين المنتجين وتجار الجملة والمشترين.",
                14,
                textDark,
                false
        );

        trust.addView(trustText);

        content.addView(trust);
    }

    LinearLayout card() {
        LinearLayout c = vertical();
        c.setPadding(18, 16, 18, 16);
        c.setBackground(background(
                Color.argb(245, 255, 252, 244),
                32
        ));
        return c;
    }

    // =========================================================
    // BUY
    // =========================================================

    void showBuy() {

        clearContent();

        addBackButton();

        TextView title = text(
                french ? "Acheter Deglet Nour" : "شراء دقلة النور",
                26,
                green,
                true
        );

        title.setGravity(Gravity.CENTER);
        content.addView(title);

        addSpace(content, 12);

        TextView info = text(
                french
                        ? "Offres disponibles de Deglet Nour en gros."
                        : "عروض دقلة النور المتوفرة بالجملة.",
                15,
                textDark,
                false
        );

        content.addView(info);

        addSpace(content, 15);

        // SAMPLE OFFER
        LinearLayout offer = card();

        TextView offerTitle = text(
                "🌴 Déglet Nour — Bechni",
                19,
                brown,
                true
        );

        offer.addView(offerTitle);

        offer.addView(text(
                french ? "Origine : Bechni" : "المصدر: بشني",
                14,
                textDark,
                false
        ));

        offer.addView(text(
                french ? "Quantité : Disponible sur demande" : "الكمية: متوفرة حسب الطلب",
                14,
                textDark,
                false
        ));

        offer.addView(text(
                french ? "Prix : À négocier" : "السعر: قابل للتفاوض",
                14,
                textDark,
                false
        ));

        Button order = button(
                french ? "Demander cette offre" : "طلب هذا العرض",
                green
        );

        order.setOnClickListener(v -> {
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

        offer.addView(order, new LinearLayout.LayoutParams(
                -1, 52
        ));

        content.addView(offer);

        addSpace(content, 12);

        TextView contactText = text(
                french
                        ? "Pour une grande quantité, contactez-nous directement."
                        : "للكميات الكبيرة، يمكنك التواصل معنا مباشرة.",
                14,
                brown,
                true
        );

        contactText.setGravity(Gravity.CENTER);
        content.addView(contactText);

        Button contact = button(
                french ? "Contacter DATRIDHA" : "تواصل مع DATRIDHA",
                darkGreen
        );

        contact.setOnClickListener(v -> showContact());

        content.addView(contact, new LinearLayout.LayoutParams(
                -1, 52
        ));
    }

    // =========================================================
    // SELL
    // =========================================================

    void showSell() {

        clearContent();

        addBackButton();

        TextView title = text(
                french ? "Vendre Deglet Nour" : "بيع دقلة النور",
                26,
                orange,
                true
        );

        title.setGravity(Gravity.CENTER);
        content.addView(title);

        addSpace(content, 10);

        content.addView(text(
                french
                        ? "Ajoutez les informations de votre offre."
                        : "أدخل معلومات عرض البيع الخاص بك.",
                15,
                textDark,
                false
        ));

        addSpace(content, 12);

        EditText name = input(
                french ? "Nom du vendeur" : "اسم البائع"
        );

        content.addView(name, new LinearLayout.LayoutParams(
                -1, 60
        ));

        addSpace(content, 8);

        EditText quantity = input(
                french ? "Quantité en kg / tonnes" : "الكمية بالكغ / الطن"
        );

        content.addView(quantity, new LinearLayout.LayoutParams(
                -1, 60
        ));

        addSpace(content, 8);

        EditText price = input(
                french ? "Prix proposé" : "السعر المقترح"
        );

        content.addView(price, new LinearLayout.LayoutParams(
                -1, 60
        ));

        addSpace(content, 8);

        EditText phone = input(
                french ? "Numéro de téléphone" : "رقم الهاتف"
        );

        content.addView(phone, new LinearLayout.LayoutParams(
                -1, 60
        ));

        addSpace(content, 8);

        EditText details = input(
                french
                        ? "Description / qualité / conditionnement"
                        : "الوصف / الجودة / طريقة التعبئة"
        );

        details.setMinHeight(100);
        content.addView(details, new LinearLayout.LayoutParams(
                -1, 110
        ));

        addSpace(content, 12);

        TextView photoInfo = text(
                french
                        ? "📷 Vous pouvez ajouter des photos de votre produit."
                        : "📷 يمكنك إضافة صور لمنتجك.",
                14,
                brown,
                true
        );

        content.addView(photoInfo);

        Button photo = button(
                french ? "Ajouter des photos" : "إضافة صور",
                brown
        );

        photo.setOnClickListener(v -> chooseImages());

        content.addView(photo, new LinearLayout.LayoutParams(
                -1, 52
        ));

        addSpace(content, 15);

        Button publish = button(
                french ? "Publier l'offre" : "نشر العرض",
                orange
        );

        publish.setOnClickListener(v -> {

            if (name.getText().toString().trim().isEmpty()
                    || quantity.getText().toString().trim().isEmpty()
                    || phone.getText().toString().trim().isEmpty()) {

                Toast.makeText(
                        this,
                        french
                                ? "Veuillez remplir les champs obligatoires."
                                : "يرجى ملء الخانات الأساسية.",
                        Toast.LENGTH_LONG
                ).show();

                return;
            }

            orders.add(
                    french
                            ? "Offre de vente publiée"
                            : "تم نشر عرض بيع"
            );

            Toast.makeText(
                    this,
                    french
                            ? "Votre offre a été enregistrée."
                            : "تم تسجيل عرضك بنجاح.",
                    Toast.LENGTH_LONG
            ).show();

            showOrders();
        });

        content.addView(publish, new LinearLayout.LayoutParams(
                -1, 58
        ));
    }

    void chooseImages() {

        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        intent.addCategory(Intent.CATEGORY_OPENABLE);

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

        addBackButton();

        TextView title = text(
                french ? "Mes commandes" : "الطلبات",
                26,
                brown,
                true
        );

        title.setGravity(Gravity.CENTER);
        content.addView(title);

        addSpace(content, 15);

        if (orders.size() == 0) {

            LinearLayout empty = card();

            TextView icon = text(
                    "📋",
                    45,
                    brown,
                    false
            );

            icon.setGravity(Gravity.CENTER);
            empty.addView(icon);

            TextView message = text(
                    french
                            ? "Aucune commande pour le moment."
                            : "لا توجد طلبات حاليًا.",
                    17,
                    textDark,
                    true
            );

            message.setGravity(Gravity.CENTER);
            empty.addView(message);

            content.addView(empty);

        } else {

            for (int i = 0; i < orders.size(); i++) {

                LinearLayout item = card();

                item.addView(text(
                        "✓  " + orders.get(i),
                        17,
                        green,
                        true
                ));

                item.addView(text(
                        french
                                ? "Statut : enregistré"
                                : "الحالة: مسجل",
                        14,
                        textDark,
                        false
                ));

                content.addView(item);

                addSpace(content, 8);
            }
        }

        addSpace(content, 15);

        Button buy = button(
                french ? "Nouvel achat" : "طلب شراء جديد",
                green
        );

        buy.setOnClickListener(v -> showBuy());

        content.addView(buy, new LinearLayout.LayoutParams(
                -1, 52
        ));
    }

    // =========================================================
    // CONTACT
    // =========================================================

    void showContact() {

        clearContent();

        addBackButton();

        TextView title = text(
                french ? "Contactez-nous" : "تواصل معنا",
                27,
                darkGreen,
                true
        );

        title.setGravity(Gravity.CENTER);
        content.addView(title);

        addSpace(content, 15);

        LinearLayout contactCard = card();

        TextView palm = text(
                "🌴",
                45,
                green,
                false
        );

        palm.setGravity(Gravity.CENTER);
        contactCard.addView(palm);

        TextView name = text(
                "DATRIDHA",
                28,
                brown,
                true
        );

        name.setGravity(Gravity.CENTER);
        contactCard.addView(name);

        addSpace(contactCard, 12);

        TextView phone = text(
                "+216 51 022 448",
                19,
                darkGreen,
                true
        );

        phone.setGravity(Gravity.CENTER);
        contactCard.addView(phone);

        TextView email = text(
                "ridhatouil1992@gmail.com",
                16,
                brown,
                false
        );

        email.setGravity(Gravity.CENTER);
        contactCard.addView(email);

        content.addView(contactCard);

        addSpace(content, 15);

        Button call = button(
                french ? "📞 Appeler" : "📞 اتصال",
                green
        );

        call.setOnClickListener(v -> {
            Intent intent = new Intent(
                    Intent.ACTION_DIAL,
                    Uri.parse("tel:+21651022448")
            );
            startActivity(intent);
        });

        content.addView(call, new LinearLayout.LayoutParams(
                -1, 54
        ));

        addSpace(content, 8);

        Button mail = button(
                french ? "✉ Envoyer un email" : "✉ إرسال بريد إلكتروني",
                brown
        );

        mail.setOnClickListener(v -> {

            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(
                    Uri.parse("mailto:ridhatouil1992@gmail.com")
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
                                ? "Aucune application email disponible."
                                : "لا يوجد تطبيق بريد إلكتروني.",
                        Toast.LENGTH_SHORT
                ).show();
            }
        });

        content.addView(mail, new LinearLayout.LayoutParams(
                -1, 54
        ));

        addSpace(content, 8);

        Button settings = button(
                french ? "Paramètres de l'application" : "إعدادات التطبيق",
                darkGreen
        );

        settings.setOnClickListener(v -> {

            try {
                Intent intent = new Intent(
                        Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                );

                intent.setData(
                        Uri.parse(
                                "package:" + getPackageName()
                        )
                );

                startActivity(intent);

            } catch (Exception e) {
                Toast.makeText(
                        this,
                        "DATRIDHA",
                        Toast.LENGTH_SHORT
                ).show();
            }
        });

        content.addView(settings, new LinearLayout.LayoutParams(
                -1, 54
        ));
    }

    // =========================================================
    // BACK BUTTON
    // =========================================================

    void addBackButton() {

        Button back = button(
                french ? "← Accueil" : "← الرئيسية",
                darkBrown
        );

        back.setOnClickListener(v -> showHome());

        content.addView(back, new LinearLayout.LayoutParams(
                -1, 48
        ));

        addSpace(content, 10);
    }
}
