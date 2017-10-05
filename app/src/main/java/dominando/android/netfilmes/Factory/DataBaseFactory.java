package dominando.android.netfilmes.Factory;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import dominando.android.netfilmes.Util.BancoUtil;

/**
 * Created by Wellington on 02/10/2017.
 */

public class DataBaseFactory extends SQLiteOpenHelper {

    public DataBaseFactory(Context context) {
        super(context, BancoUtil.NOME_BANCO, null, BancoUtil.VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + BancoUtil.TABELA_GENEROS + "("
                + BancoUtil.ID_GENERO + " integer primary key autoincrement, "
                + BancoUtil.DESCRICAO_GENERO + " text)";

        db.execSQL(sql);

        String sql2 = "CREATE TABLE " + BancoUtil.TABELA_FILMES + "("
                + BancoUtil.ID_FILME + " integer primary key autoincrement, "
                + BancoUtil.TITULO_FILME + " text, "
                + BancoUtil.GENERO_FILME + " integer not null, "
                + "FOREIGN KEY(" + BancoUtil.GENERO_FILME + ") "
                + "REFERENCES " + BancoUtil.TABELA_GENEROS + "(" + BancoUtil.ID_GENERO + "))";

        db.execSQL(sql2);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + BancoUtil.TABELA_FILMES);
        db.execSQL("DROP TABLE IF EXISTS " + BancoUtil.TABELA_GENEROS);

        onCreate(db);
    }
}
