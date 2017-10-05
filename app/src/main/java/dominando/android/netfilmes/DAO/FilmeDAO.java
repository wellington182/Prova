package dominando.android.netfilmes.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dominando.android.netfilmes.Factory.DataBaseFactory;
import dominando.android.netfilmes.Model.Filme;
import dominando.android.netfilmes.Model.Genero;
import dominando.android.netfilmes.Util.BancoUtil;

/**
 * Created by Wellington on 02/10/2017.
 */

public class FilmeDAO {
    private SQLiteDatabase db;
    private DataBaseFactory banco;


    public static final int TODOS = 1;
    public static final int POR_GENERO = 2;

    public FilmeDAO(Context context) {
        banco = new DataBaseFactory(context);
    }

    public long inserir(Filme filme) {
        ContentValues valores;
        long resultado = 0;

        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put(BancoUtil.TITULO_FILME, filme.getTitulo());
        valores.put(BancoUtil.GENERO_FILME, filme.getGenero().getId());

        resultado = db.insert(BancoUtil.TABELA_FILMES, null, valores);
        db.close();

        return resultado;
    }

    public long atualizar(Filme filme) {
        ContentValues valores;
        long resultado = 0;

        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put(BancoUtil.TITULO_FILME, filme.getTitulo());
        valores.put(BancoUtil.GENERO_FILME, filme.getGenero().getId());

        String where = BancoUtil.ID_FILME + "=" + filme.getId();

        resultado = db.update(BancoUtil.TABELA_FILMES, valores, where, null);

        db.close();

        return resultado;
    }


    public Cursor carregarDados() {
        Cursor cursor;
        String sql = "SELECT " + BancoUtil.ID_FILME + ", "
                        + BancoUtil.TITULO_FILME + ", "
                        + BancoUtil.GENERO_FILME + ", "
                        + BancoUtil.DESCRICAO_GENERO
                        + " FROM " + BancoUtil.TABELA_FILMES
                        + " INNER JOIN " + BancoUtil.TABELA_GENEROS
                        + " ON (" + BancoUtil.GENERO_FILME + " = " + BancoUtil.ID_GENERO + ")"
                        + " ORDER BY " + BancoUtil.TITULO_FILME;
        //String sql = "SELECT filme._id AS id_filme, filme.titulo AS titulo, genero._id AS id_genero, genero.descricao AS descricao FROM filme INNER JOIN genero ON (filme.genero = genero._id) ORDER BY filme.titulo";
        db = banco.getReadableDatabase();
        cursor = db.rawQuery(sql, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        db.close();
        return cursor;
    }

    public Cursor carregarDadosGeneros() {
        Cursor cursor;
        String sql = "SELECT " + BancoUtil.ID_FILME + ", "
                + BancoUtil.TITULO_FILME + ", "
                + BancoUtil.GENERO_FILME + ", "
                + BancoUtil.DESCRICAO_GENERO
                + " FROM " + BancoUtil.TABELA_FILMES
                + " INNER JOIN " + BancoUtil.TABELA_GENEROS
                + " ON (" + BancoUtil.GENERO_FILME + " = " + BancoUtil.ID_GENERO  + ")"
                + " ORDER BY " + BancoUtil.DESCRICAO_GENERO;
        db = banco.getReadableDatabase();
        cursor = db.rawQuery(sql, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        db.close();
        return cursor;
    }

    public List<Filme> carregaDadosLista(int TIPO) {
        Cursor cursor;
        if(TIPO == TODOS)
            cursor = carregarDados();
        else
            cursor = carregarDadosGeneros();

        List<Filme> filmes = new ArrayList<>();

        try {
            if(cursor.getCount() > 0) {
                do {
                    Filme filme = new Filme();
                    int ID = cursor.getInt(cursor.getColumnIndexOrThrow(BancoUtil.ID_FILME));
                    String titulo = cursor.getString(cursor.getColumnIndexOrThrow(BancoUtil.TITULO_FILME));
                    int IDGenero = cursor.getInt(cursor.getColumnIndexOrThrow(BancoUtil.GENERO_FILME));
                    String descGenero = cursor.getString(cursor.getColumnIndexOrThrow(BancoUtil.DESCRICAO_GENERO));

                    Genero genero = new Genero();
                    genero.setId(IDGenero);
                    genero.setDescricao(descGenero);

                    filme.setId(ID);
                    filme.setTitulo(titulo);
                    filme.setGenero(genero);

                    filmes.add(filme);

                } while (cursor.moveToNext());
                for (Filme f: filmes) {
                    System.out.println("Registro -> " + f.getId() + ": " + f.getTitulo());
                }
            }else {
                System.out.println("0 Registros");
            }
        } finally {
            cursor.close();
        }

        return filmes;
    }

    public void deletaRegistro(int id){
        String where = BancoUtil.ID_FILME + "=" + id;
        db = banco.getReadableDatabase();

        db.delete(BancoUtil.TABELA_FILMES,where,null);
        db.close();
    }
}
