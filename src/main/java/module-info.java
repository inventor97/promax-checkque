module com.inventor.promaxcheckque {

    requires javafx.controls;
    requires javafx.base;
    requires javafx.fxml;
    requires javafx.graphics;
    requires hibernate.entitymanager;
    requires org.hibernate.orm.core;
    requires org.hibernate.commons.annotations;
    requires org.apache.commons.codec;
    requires org.apache.commons.collections4;
    requires org.apache.commons.compress;
    requires java.sql;
    requires com.google.zxing;
    requires mysql.connector.java;
    requires org.jsoup;
    requires charm.glisten;
    requires java.persistence;
    requires java.desktop;
    requires poi;
    requires javafx.swing;
    requires java.naming;
    requires MaterialFX;


    opens com.inventor.promaxcheckque to javafx.fxml;
    exports com.inventor.promaxcheckque;
    exports com.inventor.promaxcheckque.controllers;
    opens com.inventor.promaxcheckque.controllers to javafx.fxml;
    opens com.inventor.promaxcheckque.entities to org.hibernate.orm.core;
    exports com.inventor.promaxcheckque.dto.impls;
    exports com.inventor.promaxcheckque.dto.interfaces;
    exports com.inventor.promaxcheckque.view;
    exports com.inventor.promaxcheckque.entities;
    exports com.inventor.promaxcheckque.utils;
}