package ru.lg.SovietMod.Event;

import java.util.ArrayList;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CheckBlock {
	@SubscribeEvent
	public void check(PlayerInteractEvent.RightClickBlock e) {
		BlockPos pos = e.getPos();
		EntityPlayer player = e.getEntityPlayer();
		World world = e.getEntityPlayer().getEntityWorld();
		if (world.getBlockState(pos).getBlock() == Blocks.OBSIDIAN) { // ���� ��� ���� ���������� ��������
			  ArrayList<BlockPos> poss = new ArrayList(); // ��������� ���-�� ����� ������
			  ArrayList<BlockPos> unposs = new ArrayList(); // ��������� ���-�� ������������� ������

			  poss.add(pos); // ��������� ��� ���� � ��� ���������
			  for (int a = 0; a < 6; a++) // � ���� ����� ��������� ������ ������� ������������ �����
			    unposs.add(pos.add(EnumFacing.VALUES[a].getDirectionVec()));

			  while (unposs.size() > 0) { // � ���� ����� ���������� �������� � �������� ����� ������������� ������
				  ArrayList<BlockPos> newSet = new ArrayList(); // �������� ������ ���������, �� ������ unposs
			    for (BlockPos upp : unposs) {
			      if (e.getWorld().getBlockState(upp).getBlock() == Blocks.OBSIDIAN) {
			        poss.add(upp);
			        
			        for (int a = 0; a < 6; a++) {
			          BlockPos pp = upp.add(EnumFacing.VALUES[a].getDirectionVec()); // ������� ������
			          if (!poss.contains(pp)) // ��������, ��� ����� �� ��������, ������� �� ��� ���������
			            newSet.add(pp); // ���������� ������ ������ �� ��������
			        }
			      }  
			      newSet.remove(upp); // ��������� - �������
			    }
			    unposs = newSet; // ������ ��������� � "��������"
			  }

			  System.out.println(poss.size());
			}
	}
}
