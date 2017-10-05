package dominando.android.netfilmes.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import dominando.android.netfilmes.Factory.DataBaseFactory;
import dominando.android.netfilmes.Model.Filme;
import dominando.android.netfilmes.Model.Genero;
import dominando.android.netfilmes.Util.BancoUtil;

/**
 * Created by Wellington on 03/10/2017.
 */

public class GeneroDAO {
    private SQLiteDatabase db;
    private DataBaseFactory banco;

    public GeneroDAO(Context context) {
        banco = new DataBaseFactory(context);
    }

    public long inserir(Genero genero) {
        ContentValues valores;
        long resultado;

        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put(BancoUtil.DESCRICAO_GENERO, genero.getDescricao());

        resultado = db.insert(BancoUtil.TABELA_GENEROS, null, valores);

        db.close();

        return resultado;
    }

    public long atualizar(Genero genero) {
        ContentValues valores;
        long resultado = 0;

        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put(BancoUtil.DESCRICAO_GENERO, genero.getDescricao());

        String where = BancoUtil.ID_GENERO + "=" + genero.getId();

        resultado = db.update(BancoUtil.TABELA_GENEROS, valores, where, null);

        db.close();

        return resultado;
    }

    public Cursor carregarDados() {
        System.out.println("CarregarDados()");

        Cursor cursor;
        String sql = "SELECT " + BancoUtil.ID_GENERO + ", "
                + BancoUtil.DESCRICAO_GENERO
                + " FROM " + BancoUtil.TABELA_GENEROS
                + " ORDER BY " + BancoUtil.DESCRICAO_GENERO;
        db = banco.getReadableDatabase();
        cursor = db.rawQuery(sql, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        db.close();

        return cursor;
    }

    public List<Genero> carregaDadosLista() {
        Cursor cursor;

        cursor = carregarDados();

        List<Genero> generos = new ArrayList<>();

        System.out.println("CarregarDadosLista()");

        try {
            if(cursor.getCount() > 0) {
                do {
                    Genero genero = new Genero();
                    int ID = cursor.getInt(cursor.getColumnIndexOrThrow(BancoUtil.ID_GENERO));
                    String descricao = cursor.getString(cursor.getColumnIndexOrThrow(BancoUtil.DESCRICAO_GENERO));

                    genero.setId(ID);
                    genero.setDescricao(descricao);

                    generos.add(genero);
                } while (cursor.moveToNext());
            }
        } finally {
            cursor.close();
        }

        return generos;
    }

    public void deletaRegistro(int id){
        String where = BancoUtil.ID_GENERO + "=" + id;
        db = banco.getReadableDatabase();

        db.delete(BancoUtil.TABELA_GENEROS,where,null);
        db.close();
    }
}
