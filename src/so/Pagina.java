package so;

public class Pagina {
    
    private int molduraPagina; // Número da moldura de pág
    private boolean presente;  // Bit presente/ausente
    private boolean referenciada; // Bit referenciado/não referenciado
    private boolean modificada; // Bit modificado/não modificado  

    public Pagina() {
        this.molduraPagina = -1; // Tem q ser -1 pq nao da pra iniciar com 0. Se for 0, a MMU acha q a pagina ja ta ocupando o indice 0 da memoria RAM
        this.presente = false; // false pq a pagina acabou de ser criada e so existe no HD ainda
        this.referenciada = false; // false pq a thread ainda nem comecou a rodar, então ela ainda não foi lida
        this.modificada = false; // false pq a thread ainda nem comecou a rodar, então ainda não teve operaçao de escrita
    }
    
    public int getMolduraPagina() { 
        return molduraPagina; 
    }
    
    public void setMolduraPagina(int molduraPagina) { 
        this.molduraPagina = molduraPagina; 
    }

    public boolean isPresente() { 
        return presente; 
    }

    public void setPresente(boolean presente) { 
        this.presente = presente; 
    }

    public boolean isReferenciada() { 
        return referenciada; 
    }

    public void setReferenciada(boolean referenciada) { 
        this.referenciada = referenciada; 
    }

    public boolean isModificada() { 
        return modificada; 
    }

    public void setModificada(boolean modificada) { 
        this.modificada = modificada; 
    }
}