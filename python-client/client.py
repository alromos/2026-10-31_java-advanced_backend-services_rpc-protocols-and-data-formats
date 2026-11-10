import subprocess
import sys
from pathlib import Path

import grpc

HERE = Path(__file__).resolve().parent
PROTO_DIR = HERE.parent / "proto"
GEN_DIR = HERE / "generated"


def generate_stubs():
    GEN_DIR.mkdir(exist_ok=True)
    subprocess.check_call([
        sys.executable, "-m", "grpc_tools.protoc",
        f"-I{PROTO_DIR}",
        f"--python_out={GEN_DIR}",
        f"--grpc_python_out={GEN_DIR}",
        str(PROTO_DIR / "ping.proto"),
    ])


def main():
    target = sys.argv[1] if len(sys.argv) > 1 else "localhost:8080"

    generate_stubs()
    sys.path.insert(0, str(GEN_DIR))
    import ping_pb2
    import ping_pb2_grpc

    with grpc.insecure_channel(target) as channel:
        stub = ping_pb2_grpc.PingServiceStub(channel)
        response = stub.Ping(ping_pb2.PingRequest(message="Ping"))
        print(f"Response: {response.message}")


if __name__ == "__main__":
    main()
