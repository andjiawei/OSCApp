package com.xiaov.oscapp;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.xiaov.oscapp.base.BaseApplication;
import com.xiaov.oscapp.bean.User;
import com.xiaov.oscapp.cache.DataCleanManager;
import com.xiaov.oscapp.utils.CyptoUtils;
import com.xiaov.oscapp.utils.StringUtils;

import java.util.Properties;
import java.util.UUID;

import static com.xiaov.oscapp.AppConfig.KEY_FRITST_START;
import static com.xiaov.oscapp.AppConfig.KEY_LOAD_IMAGE;
import static com.xiaov.oscapp.AppConfig.KEY_TWEET_DRAFT;

/**
 * 描述:
 * 作者：zhangjiawei
 * 时间：2016/12/30
 */
public class AppContext extends BaseApplication {

    public static final int PAGE_SIZE = 20;// 默认分页大小

    private static AppContext instance;

    private int loginUid;

    private boolean login;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        init();
        initLogin();

        /*
        // AppException 取消
        Thread.setDefaultUncaughtExceptionHandler(AppException
                .getAppExceptionHandler(this));
                */
//        UIHelper.sendBroadcastForNotice(this);
    }

    private void init() {
        // 初始化网络请求
//        AsyncHttpClient client = new AsyncHttpClient();
//        PersistentCookieStore myCookieStore = new PersistentCookieStore(this);
//        client.setCookieStore(myCookieStore);
//        ApiHttpClient.setHttpClient(client);
//        ApiHttpClient.setCookie(ApiHttpClient.getCookie(this));

//        // Log控制器
//        KJLoger.openDebutLog(BuildConfig.DEBUG);
//        TLog.DEBUG = BuildConfig.DEBUG;

        // Bitmap缓存地址
//        HttpConfig.CACHEPATH = "OSChina/imagecache";
    }

    private void initLogin() {
        User user = getLoginUser();
        if (null != user && user.getId() > 0) {
            login = true;
            loginUid = user.getId();
        } else {
            this.cleanLoginInfo();
        }
    }

    public static AppContext getInstance() {
        return instance;
    }

    public boolean containsProperty(String key) {
        Properties props = getProperties();
        return props.containsKey(key);
    }

    public void setProperties(Properties ps) {
        AppConfig.getAppConfig(this).set(ps);
    }

    public Properties getProperties() {
        return AppConfig.getAppConfig(this).get();
    }

    public void setProperty(String key, String value) {
        AppConfig.getAppConfig(this).set(key, value);
    }

    /**
     * 获取cookie时传AppConfig.CONF_COOKIE
     */
    public String getProperty(String key) {
        String res = AppConfig.getAppConfig(this).get(key);
        return res;
    }

    public void removeProperty(String... key) {
        AppConfig.getAppConfig(this).remove(key);
    }

    /**
     * 获取App唯一标识
     */
    public String getAppId() {
        String uniqueID = getProperty(AppConfig.CONF_APP_UNIQUEID);
        if (StringUtils.isEmpty(uniqueID)) {
            uniqueID = UUID.randomUUID().toString();
            setProperty(AppConfig.CONF_APP_UNIQUEID, uniqueID);
        }
        return uniqueID;
    }

    /**
     * 获取App安装包信息
     */
    public PackageInfo getPackageInfo() {
        PackageInfo info = null;
        try {
            info = getPackageManager().getPackageInfo(getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace(System.err);
        }
        if (info == null)
            info = new PackageInfo();
        return info;
    }

    /**
     * 保存登录信息
     */
    public void saveUserInfo(final User user) {
        this.loginUid = user.getId();
        this.login = true;
        setProperties(new Properties() {
            {
                setProperty("user.uid", String.valueOf(user.getId()));
                setProperty("user.name", user.getName());
                setProperty("user.face", user.getPortrait());// 用户头像-文件名
                setProperty("user.account", user.getAccount());
                setProperty("user.pwd",
                        CyptoUtils.encode("oschinaApp", user.getPwd()));
                setProperty("user.location", user.getLocation());
                setProperty("user.followers", String.valueOf(user.getFollowers()));
                setProperty("user.fans", String.valueOf(user.getFans()));
                setProperty("user.score", String.valueOf(user.getScore()));
                setProperty("user.favoritecount",
                        String.valueOf(user.getFavoritecount()));
                setProperty("user.gender", String.valueOf(user.getGender()));
                setProperty("user.isRememberMe",
                        String.valueOf(user.isRememberMe()));// 是否记住我的信息
            }
        });
    }

    /**
     * 更新用户信息
     */
    public void updateUserInfo(final User user) {
        setProperties(new Properties() {
            {
                setProperty("user.name", user.getName());
                setProperty("user.face", user.getPortrait());// 用户头像-文件名
                setProperty("user.followers",
                        String.valueOf(user.getFollowers()));
                setProperty("user.fans", String.valueOf(user.getFans()));
                setProperty("user.score", String.valueOf(user.getScore()));
                setProperty("user.favoritecount",
                        String.valueOf(user.getFavoritecount()));
                setProperty("user.gender", String.valueOf(user.getGender()));
            }
        });
    }

    /**
     * 获得登录用户的信息
     */
    public User getLoginUser() {
        User user = new User();
        user.setId(StringUtils.toInt(getProperty("user.uid"), 0));
        user.setName(getProperty("user.name"));
        user.setPortrait(getProperty("user.face"));
        user.setAccount(getProperty("user.account"));
        user.setLocation(getProperty("user.location"));
        user.setFollowers(StringUtils.toInt(getProperty("user.followers"), 0));
        user.setFans(StringUtils.toInt(getProperty("user.fans"), 0));
        user.setScore(StringUtils.toInt(getProperty("user.score"), 0));
        user.setFavoritecount(StringUtils.toInt(
                getProperty("user.favoritecount"), 0));
        user.setRememberMe(StringUtils.toBool(getProperty("user.isRememberMe")));
        user.setGender(getProperty("user.gender"));
        return user;
    }

    /**
     * 清除登录信息
     */
    public void cleanLoginInfo() {
        this.loginUid = 0;
        this.login = false;
        removeProperty("user.uid", "user.name", "user.face", "user.location",
                "user.followers", "user.fans", "user.score",
                "user.isRememberMe", "user.gender", "user.favoritecount");
    }

    public int getLoginUid() {
        return loginUid;
    }

    public boolean isLogin() {
        return login;
    }

    /**
     * 用户注销
     */
    public void Logout() {
        cleanLoginInfo();
//        ApiHttpClient.cleanCookie();
        this.cleanCookie();
        this.login = false;
        this.loginUid = 0;

//        Intent intent = new Intent(Constants.INTENT_ACTION_LOGOUT);
//        sendBroadcast(intent);
    }

    /**
     * 清除保存的缓存
     */
    public void cleanCookie() {
        removeProperty(AppConfig.CONF_COOKIE);
    }

    /**
     * 清除app缓存
     */
    public void clearAppCache() {
        DataCleanManager.cleanDatabases(this);
        // 清除数据缓存
        DataCleanManager.cleanInternalCache(this);
        DataCleanManager.cleanCustomCache(this.getExternalCacheDir());
        // 清除编辑器保存的临时内容
        Properties props = getProperties();
        for (Object key : props.keySet()) {
            String _key = key.toString();
            if (_key.startsWith("temp"))
                removeProperty(_key);
        }
//        Core.getKJBitmap().cleanCache(); //好像是清空图片缓存
    }

    public static void setLoadImage(boolean flag) {
        set(KEY_LOAD_IMAGE, flag);
    }

    public static String getTweetDraft() {
        return getPreferences().getString(KEY_TWEET_DRAFT + getInstance().getLoginUid(), "");
    }

    public static void setTweetDraft(String draft) {
        set(KEY_TWEET_DRAFT + getInstance().getLoginUid(), draft);
    }

    public static String getNoteDraft() {
        return getPreferences().getString(
                AppConfig.KEY_NOTE_DRAFT + getInstance().getLoginUid(), "");
    }

    public static boolean isFristStart() {
        return getPreferences().getBoolean(KEY_FRITST_START, true);
    }

    public static void setNoteDraft(String draft) {
        set(AppConfig.KEY_NOTE_DRAFT + getInstance().getLoginUid(), draft);
    }

    public static void setFristStart(boolean frist) {
        set(KEY_FRITST_START, frist);
    }

}
