package party.lemons.lklib.util;

import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;

import java.util.List;
import java.util.stream.Collectors;

public class ShapeUtil
{
    public static VoxelShape rotateShape(VoxelShape shape, Direction direction)
    {
        List<Box> boxes = shape.getBoundingBoxes().stream().map(b->rotate(b, direction)).collect(Collectors.toList());
        VoxelShape baseShape = VoxelShapes.empty();

        for(Box box : boxes)
        {
            baseShape = VoxelShapes.union(baseShape, VoxelShapes.cuboid(box));
        }

        return baseShape;
    }

    public static Box rotate(Box box, Direction dir)
    {
        Vec3d point1 = new Vec3d(box.minX, box.minY, box.minZ);
        Vec3d point2 = new Vec3d(box.maxX, box.maxY, box.maxZ);

        switch(dir) {
            case NORTH:
                break;
            case EAST:
                point1 = rotateHorizontal(point1, 90);
                point2 = rotateHorizontal(point2, 90);
                break;
            case SOUTH:
                point1 = rotateHorizontal(point1, 180);
                point2 = rotateHorizontal(point2, 180);
                break;
            case WEST:
                point1 = rotateHorizontal(point1, 270);
                point2 = rotateHorizontal(point2, 270);
                break;
            default:
                break;
        }

        double minX = Math.min(point1.getX(), point2.getX());
        double minY = Math.min(point1.getY(), point2.getY());
        double minZ = Math.min(point1.getZ(), point2.getZ());

        double maxX = Math.max(point1.getX(), point2.getX());
        double maxY = Math.max(point1.getY(), point2.getY());
        double maxZ = Math.max(point1.getZ(), point2.getZ());

        return new Box(minX, minY, minZ, maxX, maxY, maxZ);
    }


    public static Vec3d rotateHorizontal(Vec3d v, int degrees) {
        return rotateHorizontal(v.getX(), v.getY(), v.getZ(), degrees);
    }

    public static Vec3d rotateHorizontal(double x, double y, double z, int degrees) {
        double dx = x-0.5;
        double dz = z-0.5;
        double theta = Math.atan2(dz, dx);
        double r = Math.sqrt(dx*dx + dz*dz);

        theta += (degrees * (Math.PI/180.0));

        double xp = Math.cos(theta)*r + 0.5;
        double zp = Math.sin(theta)*r + 0.5;

        return new Vec3d(xp, y, zp);
    }
}
