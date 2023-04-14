package addressBus;
import cartridge.Cartridge;
import ram.RAMBank;

public class AddressBus{

    Cartridge cartridge;
    RAMBank RAM;
    
    public AddressBus(Cartridge cartridge){

        this.cartridge = cartridge;
        RAM = new RAMBank((0xFFFF + 1) - 0x8000);

    }
    
    public void write(int value, int address){

        if(address < 0x8000){
            System.out.println("Attempted to write to read-only ROM!!!");

        } else if(address < 0xFFFF+1) {
            int ramAddress = address - 0x8000;
            RAM.write(value, ramAddress);

        } else {
            System.out.println("Attempted to write out of bounds memory address!!!");

        }
    }

    public int read(int address){

        if(address < 0x8000){
            return cartridge.read(address);

        } else if(address < 0xFFFF+1){
            int ramAddress = address - 0x8000;
            return RAM.read(ramAddress);

        } else {
            System.out.println("Attempted to read out of bounds memory address!!!");
            return -1;
        }
    }
}