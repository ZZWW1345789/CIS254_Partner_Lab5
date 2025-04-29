module bankapp.cis254_partner_lab5 {
    requires javafx.controls;
    requires javafx.fxml;


    opens bankapp.cis254_partner_lab5 to javafx.fxml;
    exports bankapp.cis254_partner_lab5;
}