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

    LinearLayout root, content;
    boolean french = false;
    ArrayList<String> orders = new ArrayList<>();

    int brown = Color.rgb(91, 48, 20);
    int gold = Color.rgb(218, 157, 48);
    int green = Color.rgb(43, 112, 55);
    int darkGreen = Color.rgb(31, 82, 39);
    int orange = Color.rgb(196, 91, 31);
    int blue = Color.rgb(42, 103, 160);
    int purple = Color.rgb(105, 70, 135);
    int cream = Color.rgb(250, 246, 235);
    int dark = Color.rgb(55, 45, 38);

    @Override
    public void onCreate(Bundle b) {
        super.onCreate(b);
        showHome();
    }

    TextView text(String s, float size, int color, boolean bold) {
        TextView t = new TextView(this);
        t.setText(s);
        t.setTextSize(size);
        t.setTextColor(color);
        t.setGravity(Gravity.CENTER);
        t.setTypeface(null, bold ? Typeface.BOLD : Typeface.NORMAL);
        t.setPadding(16, 12, 16, 12);
        return t;
    }

    Button button(String s, int color) {
        Button b = new Button(this);
        b.setText(s);
        b.setTextSize(17);
        b.setTextColor(Color.WHITE);
        b.setAllCaps(false);
        b.setTypeface(null, Typeface.BOLD);

        GradientDrawable g = new GradientDrawable();
        g.setColor(color);
        g.setCornerRadius(28);
        b.setBackground(g);

        b.setPadding(12, 8, 12, 8);
        return b;
    }

    EditText field(String hint) {
        EditText e = new EditText(this);
        e.setHint(hint);
        e.setTextSize(16);
        e.setSingleLine(true);
        e.setTextColor(dark);
        e.setHintTextColor(Color.GRAY);
        e.setPadding(18, 5, 18, 5);

        GradientDrawable bg = new GradientDrawable();
        bg.setColor(Color.WHITE);
        bg.setCornerRadius(18);
        bg.setStroke(2, Color.rgb(225, 215, 195));
        e.setBackground(bg);

        LinearLayout.LayoutParams p =
                new LinearLayout.LayoutParams(-1, 60);
        p.setMargins(0, 7, 0, 7);

        content.addView(e, p);
        return e;
    }

    void base() {
        root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setPadding(18, 18, 18, 22);

        GradientDrawable bg = new GradientDrawable(
                GradientDrawable.Orientation.TOP_BOTTOM,
                new int[]{
                        Color.rgb(255, 250, 239),
                        Color.rgb(244, 232, 205)
                }
        );
        root.setBackground(bg);

        setContentView(root);
    }

    void header() {

        LinearLayout lang = new LinearLayout(this);
        lang.setGravity(Gravity.CENTER);
        lang.setPadding(0, 0, 0, 8);

        Button fr = button("Français", brown);
        Button ar = button("العربية", green);

        lang.addView(fr, new LinearLayout.LayoutParams(0, 52, 1));
        lang.addView(ar, new LinearLayout.LayoutParams(0, 52, 1));

        root.addView(lang);

        fr.setOnClickListener(v -> {
            french = true;
            showHome();
        });

        ar.setOnClickListener(v -> {
            french = false;
            showHome();
        });

        TextView brand = text("🌴 DATRIDHA 🌴", 32, brown, true);
        root.addView(brand, new LinearLayout.LayoutParams(-1, 70));

        TextView subtitle = text(
                french
                        ? "Deglet Nour de Bechni\nCommerce de gros"
                        : "دقلة النور من بشني\nتجارة بالجملة",
                18,
                darkGreen,
                true
        );

        root.addView(subtitle,
                new LinearLayout.LayoutParams(-1, 70));
    }

    void showHome() {

        base();
        header();

        content = new LinearLayout(this);
        content.setOrientation(LinearLayout.VERTICAL);
        content.setPadding(0, 12, 0, 0);

        root.addView(content,
                new LinearLayout.LayoutParams(-1, -1));

        Button buy = button(
                french ? "🛒 Acheter Deglet Nour"
                       : "🛒 شراء دقلة النور",
                green);

        Button sell = button(
                french ? "📦 Vendre Deglet Nour"
                       : "📦 بيع دقلة النور",
                orange);

        Button ordersBtn = button(
                french ? "📋 Mes commandes"
                       : "📋 الطلبات",
                blue);

        Button contact = button(
                french ? "☎ Nous contacter"
                       : "☎ تواصل معنا",
                purple);

        addCard(buy);
        addCard(sell);
        addCard(ordersBtn);
        addCard(contact);

        buy.setOnClickListener(v -> showBuy());
        sell.setOnClickListener(v -> showSell());
        ordersBtn.setOnClickListener(v -> showOrders());
        contact.setOnClickListener(v -> showContact());

        TextView info = text(
                french
                        ? "Qualité • Confiance • Commerce direct"
                        : "جودة • ثقة • تجارة مباشرة",
                15,
                brown,
                true
        );

        LinearLayout.LayoutParams ip =
                new LinearLayout.LayoutParams(-1, 55);
        ip.setMargins(0, 15, 0, 0);
        content.addView(info, ip);
    }

    void addCard(Button b) {
        LinearLayout.LayoutParams p =
                new LinearLayout.LayoutParams(-1, 68);
        p.setMargins(0, 7, 0, 7);
        content.addView(b, p);
    }

    void topBack(String title) {

        base();

        Button back = button(
                french ? "← Retour" : "← رجوع",
                brown
        );

        root.addView(back,
                new LinearLayout.LayoutParams(-1, 55));

        back.setOnClickListener(v -> showHome());

        TextView titleView =
                text(title, 25, brown, true);

        root.addView(titleView,
                new LinearLayout.LayoutParams(-1, 68));

        content = new LinearLayout(this);
        content.setOrientation(LinearLayout.VERTICAL);

        ScrollView scroll = new ScrollView(this);
        scroll.addView(content);

        root.addView(scroll,
                new LinearLayout.LayoutParams(-1, 0, 1));
    }

    void showBuy() {

        topBack(
                french
                        ? "Acheter Deglet Nour"
                        : "شراء دقلة النور"
        );

        content.addView(text(
                french
                        ? "Formulaire d'achat en gros"
                        : "استمارة شراء بالجملة",
                19,
                darkGreen,
                true
        ));

        EditText name = field(
                french ? "Nom / Société" : "الاسم / الشركة"
        );

        EditText phone = field(
                french ? "Téléphone" : "رقم الهاتف"
        );

        EditText quantity = field(
                french
                        ? "Quantité (kg ou tonne)"
                        : "الكمية (كغ أو طن)"
        );

        EditText price = field(
                french
                        ? "Prix souhaité (DT/kg)"
                        : "السعر المطلوب (د.ت/كغ)"
        );

        Button send = button(
                french
                        ? "Envoyer la demande"
                        : "إرسال طلب الشراء",
                green
        );

        content.addView(send,
                new LinearLayout.LayoutParams(-1, 65));

        send.setOnClickListener(v -> {

            String order =
                    (french ? "Achat" : "شراء")
                    + " - "
                    + name.getText().toString()
                    + " - "
                    + quantity.getText().toString()
                    + " - "
                    + price.getText().toString()
                    + " DT";

            orders.add(order);

            Toast.makeText(
                    this,
                    french
                            ? "Demande enregistrée"
                            : "تم تسجيل طلب الشراء",
                    Toast.LENGTH_SHORT
            ).show();

            showOrders();
        });
    }

    void showSell() {

        topBack(
                french
                        ? "Vendre Deglet Nour"
                        : "بيع دقلة النور"
        );

        content.addView(text(
                french
                        ? "Publiez votre offre"
                        : "أدخل عرض دقلة النور للبيع",
                19,
                orange,
                true
        ));

        EditText name = field(
                french ? "Nom / Producteur" : "الاسم / المنتج"
        );

        EditText phone = field(
                french ? "Téléphone" : "رقم الهاتف"
        );

        EditText quantity = field(
                french
                        ? "Quantité disponible (kg ou tonne)"
                        : "الكمية المتوفرة (كغ أو طن)"
        );

        EditText price = field(
                french
                        ? "Prix par kg (DT)"
                        : "السعر للكيلوغرام (د.ت)"
        );

        EditText quality = field(
                french
                        ? "Qualité / variété / détails"
                        : "الجودة / النوع / التفاصيل"
        );

        Button photo = button(
                french
                        ? "📷 Ajouter une photo"
                        : "📷 إضافة صورة",
                brown
        );

        content.addView(photo,
                new LinearLayout.LayoutParams(-1, 62));

        photo.setOnClickListener(v -> {

            Intent intent =
                    new Intent(Intent.ACTION_OPEN_DOCUMENT);

            intent.setType("image/*");
            intent.addCategory(
                    Intent.CATEGORY_OPENABLE
            );

            startActivityForResult(intent, 100);
        });

        Button send = button(
                french
                        ? "Publier l'offre"
                        : "نشر عرض البيع",
                orange
        );

        LinearLayout.LayoutParams sp =
                new LinearLayout.LayoutParams(-1, 65);
        sp.setMargins(0, 12, 0, 0);

        content.addView(send, sp);

        send.setOnClickListener(v -> {

            String offer =
                    (french ? "Vente" : "بيع")
                    + " - "
                    + name.getText().toString()
                    + " - "
                    + quantity.getText().toString()
                    + " - "
                    + price.getText().toString()
                    + " DT";

            orders.add(offer);

            Toast.makeText(
                    this,
                    french
                            ? "Offre publiée"
                            : "تم نشر عرض البيع",
                    Toast.LENGTH_SHORT
            ).show();

            showOrders();
        });
    }

    void showOrders() {

        topBack(
                french
                        ? "Commandes"
                        : "الطلبات"
        );

        if (orders.size() == 0) {

            content.addView(text(
                    french
                            ? "Aucune commande pour le moment."
                            : "لا توجد طلبات حاليًا.",
                    19,
                    dark,
                    false
            ));

            return;
        }

        for (String s : orders) {

            TextView item =
                    text("• " + s, 16, dark, false);

            item.setGravity(
                    Gravity.START |
                    Gravity.CENTER_VERTICAL
            );

            GradientDrawable bg =
                    new GradientDrawable();

            bg.setColor(Color.WHITE);
            bg.setCornerRadius(18);
            bg.setStroke(
                    1,
                    Color.rgb(225, 215, 195)
            );

            item.setBackground(bg);

            LinearLayout.LayoutParams p =
                    new LinearLayout.LayoutParams(-1, 70);

            p.setMargins(0, 6, 0, 6);

            content.addView(item, p);
        }
    }

    void showContact() {

        topBack(
                french
                        ? "Nous contacter"
                        : "تواصل معنا"
        );

        content.addView(text(
                "🌴 DATRIDHA",
                27,
                brown,
                true
        ));

        content.addView(text(
                french
                        ? "Commerce de Deglet Nour en gros\n\nPour toute demande concernant l'achat, la vente ou les commandes, contactez-nous."
                        : "تجارة دقلة النور بالجملة\n\nللاستفسار حول الشراء أو البيع أو الطلبات، تواصل معنا.",
                17,
                dark,
                false
        ));

        Button phone = button(
                "📞 +216 51022448",
                green
        );

        content.addView(phone,
                new LinearLayout.LayoutParams(-1, 65));

        phone.setOnClickListener(v -> {

            Intent i = new Intent(
                    Intent.ACTION_DIAL,
                    Uri.parse("tel:+21651022448")
            );

            startActivity(i);
        });

        Button email = button(
                "✉ ridhatouil1992@gmail.com",
                purple
        );

        LinearLayout.LayoutParams ep =
                new LinearLayout.LayoutParams(-1, 65);

        ep.setMargins(0, 12, 0, 0);

        content.addView(email, ep);

        email.setOnClickListener(v -> {

            Intent i = new Intent(
                    Intent.ACTION_SENDTO
            );

            i.setData(Uri.parse(
                    "mailto:ridhatouil1992@gmail.com"
            ));

            i.putExtra(
                    Intent.EXTRA_SUBJECT,
                    "DATRIDHA"
            );

            startActivity(i);
        });
    }
}
