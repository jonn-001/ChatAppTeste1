package com.example.firebaseteste.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firebaseteste.ChatActivity;
import com.example.firebaseteste.R;
import com.example.firebaseteste.model.Usuario;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ContatosAdapter extends RecyclerView.Adapter<ContatosAdapter.MyViewHolder> {

    private List<Usuario> contatos;
    private Context context;

    public ContatosAdapter(List<Usuario> listaContatos, Context c) {
        this.contatos = listaContatos;
        this.context = c;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_contatos,parent,false);
        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position){

        Usuario usuario = contatos.get(position);

        holder.nome.setText(usuario.getNome());
        holder.email.setText(usuario.getEmail());
        holder.foto.setImageResource(R.drawable.default_profile);

        holder.contatosLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Usuario usuarioSelecionado = contatos.get(position);
                Intent intent = new Intent(context, ChatActivity.class);
                intent.putExtra("chatContato",usuarioSelecionado);
                context.startActivity(intent);
            }
        });



    }

    @Override
    public int getItemCount() {
        return contatos.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        CircleImageView foto;
        TextView nome,email;
        LinearLayout contatosLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            foto = itemView.findViewById(R.id.id_fotopadrao);
            contatosLayout = itemView.findViewById(R.id.id_listaContatosAdapterLayout);
            nome = itemView.findViewById(R.id.id_contatoNome);
            email = itemView.findViewById(R.id.id_contatoEmail);

        }
    }

}
