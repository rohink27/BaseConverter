package CalculationClass;

import java.util.*;
public class base_conversion
{
    String n;
    int base, conversion;
    public base_conversion(String s, int b, int c)
    {
        n=s.toUpperCase();
        base=b;
        conversion=c;
    }

    public static void main(String[] args)
    {
        Scanner in= new Scanner(System.in);
        System.out.println("Enter a number");
        String n=in.nextLine();
        System.out.println("Enter base");
        int base= in.nextInt();
        System.out.println("Enter base to convert to");
        int conversion= in.nextInt();
        base_conversion ob= new base_conversion(n,base,conversion);
        if(!ob.check_validity())
        {
            System.out.println("Wrong base");
            System.exit(0);
        }
        System.out.println(ob.check_validity());
        String fin_ans= ob.convert();
        System.out.println("The converted number is "+ fin_ans);
    }
   public boolean check_validity()
    {
        
        int points,minus;
        points=minus=0;
        if (base<2|| base>36 || conversion<2 || conversion>36)
        return false;
        
        for(int i=0; i<n.length(); i++)
        {
            if(i!='-' && i!='.' && i!=' ' && i!='\t'&& n.charAt(i)-48>=base && base<=10)
            return false;
            else if(i!='-' && i!='.' && i!=' ' && i!='\t'&& n.charAt(i)-64>base-10)
            return false;
            if(n.charAt(i)=='-')
            minus++;
            if(n.charAt(i)=='.')
            points++;

        }
        String t= n.trim();
        if(t.charAt(0)!='-'&& minus>0)
        return false;
        if(points>1|| points<0)
        return false;
        return true;
    }
    public String convert()
    {
        if(!check_validity())
        return "";
        int characters, numbers, point, decimals;
        characters=numbers=decimals=0;
        point =-1;
        boolean minus=false;

        for(int i=0; i<n.length(); i++)
        {
            if(n.charAt(i)!=' '&& n.charAt(i)!='\t')
            {
                 characters++;
            if(n.charAt(i)=='.')
            {
                point=i;
                continue;
            }
            if(n.charAt(i)=='-')
            {
                minus=true;
                continue;
            }
            if(point==-1)
            numbers++;
            else if(point!=-1)
            decimals++;
            }
        }
        
        
        double intermediate=0.0;
        int i=0,power=numbers-1;
        int loop_terminator=0;
        if(base<=10){
        for( ; loop_terminator<(numbers+decimals); )
        {
            char c= n.charAt(i++);
            if(c==' '|| c=='\t'|| c=='.'||c=='-'){
                
                continue;

            }
            
            loop_terminator++;
            intermediate+= (c-48)*Math.pow(base, power--);
           
        }
        }
        else{
            for(;loop_terminator<(numbers+decimals) ; )
        {
            char c= n.charAt(i++);
            if(c==' '|| c=='\t'|| c=='.'||c=='-'){
                
                continue;

            }
            loop_terminator++;
            
            
            if(Character.isDigit(c))
            intermediate+= (c-48)*Math.pow(base, power--);
            else
            intermediate+= (c-55)*Math.pow(base,power--);
            

        }

        }
        System.out.println(intermediate);
        String number="";
        int half1=0;
        double half2=0.0;
        if(point!=-1)
        {
            half1= (int)intermediate;
            half2= intermediate-half1;
        }
        else
        half1=(int)intermediate;
        while((half1)!=0)
        {
            int k= half1%conversion;
            if(k>=10)
            number= (char)(k+55)+ number;
            else
            number= k + number;
            half1/=conversion;
        }
        if(point!=-1){
        number+='.';
        int counter=0;
        double frac;
        
        while(counter<10)
        {
            frac= half2*conversion;
            
            int fra= (int)frac;
            if(fra>=10)
            number+=(char)(fra+55);
            else
            number+=fra;
            frac= frac-fra;
            half2=frac;
            if(frac==0 || (frac<0.0000001))
            break;
            
            counter++;
        }
        }
        if(minus)
        number='-'+ number;
        return number;
        }


        

           


        }
    
