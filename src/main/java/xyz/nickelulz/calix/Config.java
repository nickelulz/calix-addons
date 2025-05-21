package xyz.nickelulz.calix;

import java.time.format.DateTimeFormatter;

import xyz.nickelulz.calix.Calix;

public class Config {
    /* Server Info */
    
    public final String SERVER_NAME;
    public final String SERVER_ADDRESS;

    /* Database */
    
    public final String DATABASE_FILENAME;
    public final String DATABASE_USERNAME;
    public final String DATABASE_PASSWORD;

    /* Hits */
    
    public final int MINIMUM_HIT_PRICE;
    public final String TARGET_WARNING;
    
    /* Cooldowns */
    
    public final int PLACING_COOLDOWN;
    public final int CONTRACTING_COOLDOWN;
    public final int TARGET_COOLDOWN;
    public final int WAR_COOLDOWN;
    
    /* Error Messages */
    
    public final String CONTRACTOR_NOT_FOUND;
    public final String TARGET_NOT_FOUND;
    public final String HIRER_NOT_FOUND;
    public final String PLAYER_NOT_FOUND;
    public final String INVALID_AMOUNT;
    public final String PRICE_TOO_LOW;
    public final String PRICE_TOO_HIGH;
    public final String TARGET_IS_BUSY;
    public final String USER_BUSY;
    public final String TOO_MANY_HITS;
    public final String CONTRACTOR_UNDER_COOLDOWN;
    public final String HIRER_UNDER_COOLDOWN;
    public final String TARGET_UNDER_COOLDOWN;
    public final String HIRER_IS_TARGET;
    public final String CONTRACTOR_IS_TARGET;
    public final String HIRER_IS_CONTRACTOR;
    
    /*
     * Inherent Constants
     */
    public final DateTimeFormatter VISUAL_DATE_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    public final DateTimeFormatter DATA_DATE_FORMAT = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
    
    public Config(Calix instance)
    {
        FileConfiguration config = instance.getConfig();

        // Server Info
        SERVER_NAME    = config.getString("server.name");
        SERVER_ADDRESS = config.getString("server.address");

        // Hits
        MINIMUM_HIT_PRICE = config.getInt("hits.minimum-price");
        TARGET_WARNING    = config.getString("hits.target-warning-message");
        
        // Cooldowns
        PLACING_COOLDOWN     = config.getInt("cooldowns.placing-hits-cooldown");
        CONTRACTING_COOLDOWN = config.getInt("cooldowns.accepting-contracts-cooldown");
        TARGET_COOLDOWN      = config.getInt("cooldowns.target-cooldown");
        WAR_COOLDOWN         = config.getInt("cooldowns.war-participation-cooldown");
        
        // Database
        DATABASE_FILENAME = config.getString("database.filename");
        DATABASE_USERNAME = config.getString("database.username");
        DATABASE_PASSWORD = config.getString("database.password");
        
        // Error Messages
        CONTRACTOR_NOT_FOUND      = config.getString("error.contractor-not-found");
        TARGET_NOT_FOUND          = config.getString("error.target-not-found");
        HIRER_NOT_FOUND           = config.getString("error.hirer-not-found");
        PLAYER_NOT_FOUND          = config.getString("error.player-not-found");
        INVALID_AMOUNT            = config.getString("error.invalid-amount");
        PRICE_TOO_LOW             = config.getString("error.price-too-low");
        TARGET_IS_BUSY            = config.getString("error.target-is-busy");
        USER_IS_BUSY              = config.getString("error.user-is-busy");
        TOO_MANY_HITS             = config.getString("error.too-many-hits");
        CONTRACTOR_UNDER_COOLDOWN = config.getString("error.contractor-under-cooldown");
        HIRER_UNDER_COOLDOWN      = config.getString("error.hirer-under-cooldown");
        TARGET_UNDER_COOLDOWN     = config.getString("error.target-under-cooldown");
        HIRER_IS_TARGET           = config.getString("error.hirer-is-target");
        CONTRACTOR_IS_TARGET      = config.getString("error.contractor-is-target");
        HIRER_IS_CONTRACTOR       = config.getString("error.hirer-is-contractor");
    }
}
