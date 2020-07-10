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
import com.example.firebaseteste.helper.UsuarioFirebase;
import com.example.firebaseteste.model.Conversa;
import com.example.firebaseteste.model.Usuario;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ConversasAdapter extends RecyclerView.Adapter<ConversasAdapter.MyViewHolder> {

    private List<Conversa> conversas;
    private Context context;

    public ConversasAdapter(List<Conversa> lista, Context c) {
        this.conversas = lista;
        this.context = c;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLista = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_contatos,parent,false);
        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        final Conversa conversa = conversas.get(position);
        holder.ultimaMensagem.setText(conversa.getUltimaMensagem());

        Usuario usuario = conversa.getUsuarioExibicao();
        holder.nome.setText(usuario.getNome());
        holder.foto.setImageResource(R.drawable.default_profile);

        holder.contatosLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Conversa conversaSelecionada = conversas.get(position);
                Intent intent = new Intent(context, ChatActivity.class);
                intent.putExtra("chatContato",conversaSelecionada.getUsuarioExibicao());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return conversas.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        CircleImageView foto;
        TextView nome,ultimaMensagem;
        LinearLayout contatosLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            contatosLayout = itemView.findViewById(R.id.id_listaContatosAdapterLayout);
            foto = itemView.findViewById(R.id.id_fotopadrao);
            nome = itemView.findViewById(R.id.id_contatoNome);
            ultimaMensagem = itemView.findViewById(R.id.id_contatoEmail);

        }
    }
}
