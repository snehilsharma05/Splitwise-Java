/*
Test case:
3
Rahul Ajay 100
Ajay Neha 50
Neha Rahul 40
*/
import java.util.*;

class Pair{
    String name;
    int amount;
    Pair(String n,int amt)
    {
        name = n;
        amount = amt;
    }
    public String toString(){
        
        return name+"-> "+amount; 
    }
}
class Custom implements Comparator<Pair>{
    public int compare(Pair a,Pair b)
    {
        if(a.amount<b.amount)
        return -1;
        else if(a.amount>b.amount)
        return 1;
        else return 0;
    }
}
public class MyClass {
    public static void main(String args[]) {
        Scanner s = new Scanner(System.in);
        int transactions = s.nextInt(),amount,count=0;
        HashMap<String,Integer> map = new HashMap<>();
        String p1,p2;
        Integer p1_amt,p2_amt;
        while(transactions-->0)
        {
            p1 = s.next();
            p2 = s.next();
            amount = s.nextInt();
            p1_amt = map.get(p1);
            p2_amt = map.get(p2);
            if(p1_amt==null)
            map.put(p1,-amount);
            else map.put(p1,p1_amt-amount);
            
            if(p2_amt==null)
            map.put(p2,amount);
            else map.put(p2,p2_amt+amount);
            
        }
        
        LinkedList<Pair> net = new LinkedList<>();
        for(Map.Entry<String,Integer> i:map.entrySet())
        {
            if(i.getValue()!=0)
            net.add(new Pair(i.getKey(),i.getValue()));
        }
        
        Collections.sort(net,new Custom());
        System.out.println(net);
        
        while(net.isEmpty()==false)
        {
            Pair first = net.getFirst();
            Pair second = net.getLast();
            net.removeFirst(); net.removeLast();
            int debit_amt = first.amount;
            int credit_amt = second.amount;
            int settled_amt = Math.min(-debit_amt,credit_amt);
            
            debit_amt += settled_amt;
            credit_amt -= settled_amt;
            count++;
            System.out.println(first.name+" will give "+settled_amt+" to "+second.name);
            
            if(debit_amt!=0)
            net.add(new Pair(first.name,debit_amt));
            if(credit_amt!=0)
            net.add(new Pair(second.name,credit_amt));
            Collections.sort(net,new Custom());
        }
        System.out.println("Total Transactions to be made: "+count);
    }
}