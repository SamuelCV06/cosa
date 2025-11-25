package tp2;

import tp2.arbol.*;
import javax.swing.*;
import java.awt.*;

public class PrintarArbol extends JFrame
{
    private JPanel ventana;
    private NodoArbol raiz;
    private final int nodoWidth =100;
        private final int nodoHeight =40;

    
     
    public PrintarArbol(ArbolBruto arbol, int n) 
    {
            super("ArbolAleatorio #"+n);

            this.raiz = arbol.getRaiz();

    }
    public int drawTree(Graphics g, NodoArbol nodo, int num1, int num2, int y)
    {
        int m =(num1+num2)/2;
        int node =m-(nodoWidth/2);
        
        String t = "";
        if(nodo.getIzq() == null && nodo.getDer() == null)
        {
             g.setColor(Color.green);
             int clase = nodo.getValorHoja();
             if(clase == -1)
             {
                t ="Pregunta";
             }
             else if (clase == 0)
             {
                t = "No Sobrevivio";
             }
             else if (clase == 1)
            {
                t = "Sobrevivio";
            }
        }
        else
        {
            g.setColor(Color.blue);
            t = String.valueOf(nodo);

        }
        g.fillRect(node, y, nodoWidth, nodoHeight);
        g.setColor(Color.white);
        g.setFont(new Font("Arial",Font.BOLD,20));
        g.drawString(t, node + 5, y + 25);

        if(nodo.getIzq() != null)
        {
            int num3 = drawTree(g, nodo.getIzq(), num1, m, y+50);
            g.setColor(Color.black);
            g.drawLine(m+25, y+40, num3 +25, y+50);
        }
        if(nodo.getDer() != null)
        {
            int num3 = drawTree(g, nodo.getDer(), m, num2, y+50);
            g.setColor(Color.black);

            g.drawLine(m+25, y+40, num3+25, y+50);
        }
        return m;
    }

     @Override
    public void paint(Graphics g) {
        super.paint(g);
        drawTree(g, this.raiz, 0, this.getWidth()-25, 100);
    }
}
