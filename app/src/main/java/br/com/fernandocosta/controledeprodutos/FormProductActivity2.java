package br.com.fernandocosta.controledeprodutos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class FormProductActivity2 extends AppCompatActivity {

    private EditText edit_produto;
    private EditText edit_quantidade;
    private EditText edit_valor;
    private ProdutoDAO produtoDAO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_product2);

        produtoDAO = new ProdutoDAO(this);

        edit_produto  = findViewById(R.id.edit_produto);
        edit_quantidade = findViewById(R.id.edit_quantidade);
        edit_valor = findViewById(R.id.edit_valor);
    }

    public void salvarProduto(View view){
        String nomeProduto = edit_produto.getText().toString();
        String quantidadeProduto = edit_quantidade.getText().toString();
        String valorProduto = edit_valor.getText().toString();

        if(!nomeProduto.isEmpty()){

            if(!quantidadeProduto.isEmpty()){
                int quantidadeProdutoValida = Integer.parseInt(quantidadeProduto);

                if(quantidadeProdutoValida >= 1){

                    if(!valorProduto.isEmpty()){

                        double valorProdutoValido = Double.parseDouble(valorProduto);

                        if(valorProdutoValido > 0){

                            Produto produto = new Produto();
                                produto.setNome(nomeProduto);
                            produto.setEstoque(quantidadeProdutoValida);
                            produto.setValorProduto(valorProdutoValido);

                            produtoDAO.salvarProduto(produto);

                            finish();


                        } else {
                            edit_valor.requestFocus();
                            edit_valor.setError("Informe o valor do produto.");
                        }

                    } else{
                        edit_valor.requestFocus();
                        edit_valor.setError("Informe o valor do produto.");
                    }

                } else {
                    edit_quantidade.requestFocus();
                    edit_quantidade.setError("Informe um valor maior que zero.");
                }

            } else{
                edit_quantidade.requestFocus();
                edit_quantidade.setError("Informe um valor válido.");

            }

        } else {
            edit_produto.requestFocus();
            edit_produto.setError("Informe o nome do produto.");
        }
    }

}